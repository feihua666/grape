package grape.common.rest.validation.props;

import grape.common.rest.validation.form.ValidContext;
import grape.common.rest.validation.form.ValidResult;
import grape.common.tools.ToolService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by yangwei
 * Created at 2019/12/6 9:53
 */
public interface IPropValidator<T extends Annotation> extends ToolService {

    boolean support(Annotation annotation);

    boolean valid(Object value, T annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext);
}
