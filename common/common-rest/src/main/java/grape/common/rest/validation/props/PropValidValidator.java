package grape.common.rest.validation.props;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import grape.common.rest.validation.ValidHelper;
import grape.common.rest.validation.cross.depend.DependField;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主要用来属性验证扩展
 * Created by yangwei
 * Created at 2019/11/28 8:57
 */
@Component
@Slf4j
public class PropValidValidator implements ConstraintValidator<PropValid, Object>, ToolService {

    @Autowired
    private ValidHelper validHelper;
    private String message;
    private String dictIdProp[];
    @Autowired
    private List<IPropValidator> iPropValidators;
    @Autowired(required = false)
    private DictService dictService;

    @Override
    public void initialize(PropValid constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.dictIdProp = constraintAnnotation.dictIdProp();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 不验证空
        if (value == null) {
            return true;
        }
        ValidContext validContext = new ValidContext();
        ValidResult validResult = new ValidResult();

            validContext.setDictIdCode(validHelper.getDictCodeMap(dictIdProp,value));
        for (Field field : ReflectUtil.getFields(value.getClass())) {
            Annotation[] annotations = AnnotationUtil.getAnnotations(field, false);
            for (Annotation annotation : annotations) {
                for (IPropValidator iPropValidator : iPropValidators) {
                    if(iPropValidator.support(annotation)){
                        if(!iPropValidator.valid(value,annotation,ReflectUtil.getFieldValue(value,field),field,validResult,validContext)){
                            String reportOn = validResult.getReportOn();
                            String message = validResult.getErrorMsg();
                            if(isStrEmpty(reportOn)){
                                reportOn = field.getName();
                            }
                            if(isStrEmpty(message)){
                                message = this.message;
                            }
                            validHelper.reportMessageOnProp(reportOn,message,constraintValidatorContext);
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
}
