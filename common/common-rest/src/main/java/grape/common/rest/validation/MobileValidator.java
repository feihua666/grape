package grape.common.rest.validation;

import cn.hutool.core.lang.Validator;
import grape.common.tools.ToolService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by yangwei
 * Created at 2019/11/27 13:52
 */
@Component
public class MobileValidator implements ConstraintValidator<Mobile, String>, ToolService {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!isStrEmpty(value)){
            return Validator.isMobile(value);
        }
        return true;
    }
}
