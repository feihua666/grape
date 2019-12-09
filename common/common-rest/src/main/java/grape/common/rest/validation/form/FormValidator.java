package grape.common.rest.validation.form;

import grape.common.rest.validation.ValidHelper;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by yangwei
 * Created at 2019/11/28 8:57
 */
@Component
@Slf4j
public class FormValidator implements ConstraintValidator<Form, Object>, ToolService {


    @Autowired
    private ValidHelper validHelper;
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

        if(value instanceof IFormValid){

            validContext.setDictIdCode(validHelper.getDictCodeMap(dictIdProp,value));
            boolean r = ((IFormValid) value).valid(validResult,validContext);
            // 如果验证不通过，处理消息
            if (! r) {
                String reportOn = validResult.getReportOn();
                String message = validResult.getErrorMsg();
                if(isStrEmpty(reportOn)){
                    reportOn = this.reportOn;
                }
                if(isStrEmpty(message)){
                    message = this.message;
                }
                validHelper.reportMessageOnProp(reportOn,message,constraintValidatorContext);
            }
            return  r;
        }else {
            log.warn("表单验证：requestId=[{}]要验证表单需要实现[IFormValid]接口，已经默认验证通过。当前正在验证的表单[{}]", RequestIdTool.getRequestId(),value.getClass().getName());
        }
        return true;
    }
}
