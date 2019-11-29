package grape.common.rest.validation.cross.depend;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import grape.common.rest.validation.form.Form;
import grape.common.rest.validation.form.IFormValid;
import grape.common.rest.validation.form.ValidContext;
import grape.common.rest.validation.form.ValidResult;
import grape.common.service.common.DictService;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/11/28 8:57
 */
@Component
@Slf4j
public class DependFieldValidator implements ConstraintValidator<DependField, Object>, ToolService {

    @Autowired(required = false)
    private DictService dictService;
    private boolean dict;
    private String dependProp;
    private String targetProp;
    private String equal;
    private String pattern;
    private String patternAlias;
    private String message;
    private String reportOn;

    public static final String pattern_alias_mobile = "mobile";
    public static final String pattern_alias_email = "email";

    private static Map<String,String> patternAliasMap = null;
    static {
        patternAliasMap = new HashMap<>();
        patternAliasMap.put(pattern_alias_mobile, PatternPool.MOBILE.pattern());
        patternAliasMap.put(pattern_alias_email, PatternPool.EMAIL.pattern());
    }
    @Override
    public void initialize(DependField constraintAnnotation) {

        this.dict = constraintAnnotation.dict();
        this.dependProp = constraintAnnotation.dependProp();
        this.targetProp = constraintAnnotation.targetProp();
        this.equal = constraintAnnotation.equal();
        this.pattern = constraintAnnotation.pattern();
        this.patternAlias = constraintAnnotation.patternAlias();
        this.message = constraintAnnotation.message();
        this.reportOn = constraintAnnotation.reportOn();
        this.reportOn = constraintAnnotation.reportOn();

        if(isStrEmpty(this.pattern) && !isStrEmpty(this.patternAlias)){

            this.pattern = patternAliasMap.get(this.patternAlias);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 不验证空
        if (value == null) {
            return true;
        }
        Object dependValue = ReflectUtil.getFieldValue(value,this.dependProp);
        Object targetValue = ReflectUtil.getFieldValue(value,this.targetProp);
        // 字典转换
        if(this.dict){
            log.debug("依赖字段验证：requestId=[{}],设置了字典属性dependProp=[{}]",RequestIdTool.getRequestId(),this.dependProp);
            if(this.dictService != null){
                if(dependValue != null){
                    dependValue = this.dictService.getCodeById(dependValue.toString());
                }else {
                    log.debug("依赖字段验证：requestId=[{}],字典属性[{}]值为空",RequestIdTool.getRequestId(),this.dependProp);
                }
            }else {
                throw new RuntimeException("依赖字段验证指定了字典属性但字典服务[DictService]没有被注入，请检查配置或需要实现[dictService]接口");
            }
        }else {
            log.debug("依赖字段验证：requestId=[{}],没有指定字典属性，将直接用dependProp对应的值",RequestIdTool.getRequestId());
        }
        log.debug("依赖字段验证：requestId=[{}],dependValue=[{}],equal=[{}],targetValue=[{}]",
                RequestIdTool.getRequestId(),
                dependValue,
                this.equal,
                targetValue

        );
        // 判断字符串相等只考虑了字符串的情况
        if(dependValue != null && this.equal != null && isEqual(dependValue.toString(),this.equal)){

            // 验证pattern
            if (targetValue != null && !ReUtil.isMatch(this.pattern,targetValue.toString())) {
                // 验证错误
                if (!this.reportOn.isEmpty()) {
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addPropertyNode(this.reportOn).addConstraintViolation();
                }
                return false;
            }
        }
        return true;
    }
}
