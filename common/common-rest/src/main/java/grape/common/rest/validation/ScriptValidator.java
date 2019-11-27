package grape.common.rest.validation;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import grape.common.service.common.DictService;
import grape.common.tools.ToolService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorInitializationContext;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.hibernate.validator.internal.util.Contracts;
import org.hibernate.validator.internal.util.logging.Messages;
import org.hibernate.validator.spi.scripting.ScriptEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/11/27 14:35
 */
@Component
public class ScriptValidator  implements HibernateConstraintValidator<Script, Object> , ToolService {

    private static List<String> innerMethod = null;


    protected String languageName;
    protected String script;
    private String alias;
    private String reportOn;
    private String message;

    private String [] dictIdProp;

    protected ScriptEvaluator scriptEvaluator;

    @Autowired
    private DictService dictService;


    static {

        innerMethod = new ArrayList<>();
        innerMethod.add("var mobile = function (mobile){return !mobile? true: new RegExp(/"+PatternPool.MOBILE.pattern()+"/).test(mobile)};");
        innerMethod.add("var email = function (email){return !email? true: new RegExp(/"+PatternPool.EMAIL.pattern()+"/).test(email)};");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, Object> bindings = CollectionHelper.newHashMap();
        bindings.put(alias, value);

        // 前置方法
        String newScript = resolveScript(value,script);
        Object r = this.scriptEvaluator.evaluate(newScript, bindings);
        boolean validationResult = Boolean.TRUE.equals(r);
        if (!validationResult && !this.reportOn.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addPropertyNode(this.reportOn).addConstraintViolation();
        }

        return validationResult;
    }

    @Override
    public void initialize(ConstraintDescriptor<Script> constraintDescriptor, HibernateConstraintValidatorInitializationContext initializationContext) {
        Script constraintAnnotation = (Script)constraintDescriptor.getAnnotation();
        this.validateParameters(constraintAnnotation);
        this.languageName = constraintAnnotation.lang();
        this.script = constraintAnnotation.script();
        this.alias = constraintAnnotation.alias();
        this.reportOn = constraintAnnotation.reportOn();
        this.message = constraintAnnotation.message();
        this.dictIdProp = constraintAnnotation.dictIdProp();
        this.scriptEvaluator = initializationContext.getScriptEvaluatorForLanguage(languageName);
    }

    private void validateParameters(Script constraintAnnotation) {
        Contracts.assertNotEmpty(constraintAnnotation.script(), Messages.MESSAGES.parameterMustNotBeEmpty("script"));
        Contracts.assertNotEmpty(constraintAnnotation.lang(), Messages.MESSAGES.parameterMustNotBeEmpty("lang"));
        Contracts.assertNotEmpty(constraintAnnotation.alias(), Messages.MESSAGES.parameterMustNotBeEmpty("alias"));
    }

    /**
     *
     * 将字典id转code
     * @param value
     * @param script
     * @return 如果这里有异常肯定是脚本写的不对,就算有异常也不处理，这是编码级bug，应该在上线前会测试出来
     */
    private String resolveScript(Object value,String script){
        Map<String, Object> dictCodeObj = CollectionHelper.newHashMap();
        StringBuffer inner = new StringBuffer();
        for (String method : innerMethod) {
            inner.append(method);
        }

        if (this.dictIdProp != null) {
            for (String dictIdP : dictIdProp) {
                Object fieldValue = ReflectUtil.getFieldValue(value, dictIdP);
                if (fieldValue != null) {
                    String code = dictService.getCodeById(fieldValue.toString());
                    dictCodeObj.put(fieldValue.toString(), code);
                }
            }
            inner.append("var dictCodeObj = " + JSONUtil.toJsonStr(dictCodeObj) + ";");
            inner.append("var getDictCodeById = function (id){return dictCodeObj[id]};");
            inner.append(script);
        }
        return inner.toString();
    }
}
