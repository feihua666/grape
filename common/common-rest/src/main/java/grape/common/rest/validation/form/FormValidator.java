package grape.common.rest.validation.form;

import cn.hutool.core.util.ReflectUtil;
import grape.common.service.common.DictService;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/11/28 8:57
 */
@Component
@Slf4j
public class FormValidator implements ConstraintValidator<Form, Object>, ToolService {

    @Autowired(required = false)
    private DictService dictService;
    private String dictIdProp[];
    private String message;
    private String reportOn;

    @Override
    public void initialize(Form constraintAnnotation) {

        this.dictIdProp = constraintAnnotation.dictIdProp();
        this.message = constraintAnnotation.message();
        this.reportOn = constraintAnnotation.reportOn();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 不验证空
        if (value == null) {
            return true;
        }
        ValidContext validContext = new ValidContext();
        ValidResult validResult = new ValidResult();

        Map<String, String> dictCodeObj = CollectionHelper.newHashMap();
        if(value instanceof IFormValid){
            if(this.dictIdProp != null){
                if(dictService != null){
                    for (String dictIdP : dictIdProp) {
                        Object fieldValue = ReflectUtil.getFieldValue(value, dictIdP);
                        if (fieldValue != null) {
                            String code = dictService.getCodeById(fieldValue.toString());
                            dictCodeObj.put(fieldValue.toString(), code);
                        }
                    }
                }else {
                    throw new RuntimeException("表单验证指定了字典属性但字典服务[DictService]没有被注入，请检查配置或需要实现[dictService]接口");
                }
            }else {
                log.debug("表单验证：requestId=[],未指定字典属性",RequestIdTool.getRequestId());
            }
            validContext.setDictIdCode(dictCodeObj);
            ((IFormValid) value).valid(validResult,validContext);
            // 如果验证不通过，处理消息
            if (!validResult.isResult()) {
                String reportOn = validResult.getReportOn();
                String message = validResult.getErrorMsg();
                if(isStrEmpty(reportOn)){
                    reportOn = this.reportOn;
                }
                if(isStrEmpty(message)){
                    message = this.message;
                }
                if(!isStrEmpty(reportOn) && !isStrEmpty(message)){
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(reportOn).addConstraintViolation();
                }else {
                    throw new RuntimeException("表单验证未通过，必须指定错误消息和报告字段。当前正在验证的表单["+ value.getClass().getName() +"]");
                }
            }
            return validResult.isResult();
        }else {
            log.warn("表单验证：requestId=[{}]要验证表单需要实现[IFormValid]接口，已经默认验证通过。当前正在验证的表单[{}]", RequestIdTool.getRequestId(),value.getClass().getName());
        }
        return true;
    }
}
