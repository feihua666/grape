package grape.common.rest.validation.props.propvalidator;

import grape.common.rest.validation.form.ValidContext;
import grape.common.rest.validation.form.ValidResult;
import grape.common.rest.validation.props.IPropValidator;
import grape.common.rest.validation.props.PropValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by yangwei
 * Created at 2019/12/6 9:56
 */
@Component
public class DependListValidator implements IPropValidator<PropValid.DependList> {


    @Autowired
    private DependValidator dependValidator;

    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.DependList;
    }

    @Override
    public boolean valid(Object value, PropValid.DependList annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {
        for (PropValid.Depend depend : annotation.value()) {
            if(!dependValidator.valid(value,depend,fieldValue,field,validResult,validContext)){
                return false;
            }
        }
        return true;
    }
}
