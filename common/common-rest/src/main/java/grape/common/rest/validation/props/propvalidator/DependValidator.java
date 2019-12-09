package grape.common.rest.validation.props.propvalidator;

import cn.hutool.core.util.ReflectUtil;
import grape.common.rest.validation.form.ValidContext;
import grape.common.rest.validation.form.ValidResult;
import grape.common.rest.validation.props.IPropValidator;
import grape.common.rest.validation.props.PropValid;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by yangwei
 * Created at 2019/12/6 9:56
 */
@Component
public class DependValidator implements IPropValidator<PropValid.Depend> {
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.Depend;
    }

    @Override
    public boolean valid(Object value, PropValid.Depend annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {

        Object dependOnValue = ReflectUtil.getFieldValue(value, annotation.dependProp());
        if (dependOnValue != null && (fieldValue == null || (fieldValue instanceof String && isStrEmpty(((String) fieldValue))) )) {
            validResult.setErrorMsg(annotation.message());
            return false;
        }

        return true;
    }
}
