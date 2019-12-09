package grape.common.rest.validation.props.propvalidator;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import grape.common.rest.validation.ValidHelper;
import grape.common.rest.validation.form.ValidContext;
import grape.common.rest.validation.form.ValidResult;
import grape.common.rest.validation.props.IPropValidator;
import grape.common.rest.validation.props.PropValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/12/6 9:56
 */
@Component
public class DependConditionValidator implements IPropValidator<PropValid.DependCondition> {


    @Autowired
    private ValidHelper validHelper;

    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.DependCondition;
    }

    @Override
    public boolean valid(Object value, PropValid.DependCondition annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {

        Object dependOnValue = ReflectUtil.getFieldValue(value, annotation.dependProp());
        if(annotation.dependDict() && dependOnValue != null){
            Map<String, String> dictIdCode = validContext.getDictIdCode();
            if (!dictIdCode.containsKey(dependOnValue)) {
                dictIdCode = validHelper.getDictCodeMap(new String[]{annotation.dependProp()}, value);
            }
            String code = dictIdCode.get(dependOnValue);
            dependOnValue = code;

        }

        if (dependOnValue != null) {
            boolean r = true;
            if(annotation.ifEqual().equals(dependOnValue)){
                String pattern = annotation.thenPattern();
                if(isStrEmpty(pattern) && !isStrEmpty(annotation.patternAlias())){
                    pattern = validHelper.getPatternAliasMap().get(annotation.patternAlias());
                    if (!isStrEmpty(pattern)) {
                        r = ReUtil.isMatch(pattern,fieldValue.toString());
                    }else {
                        throw new RuntimeException("不支持的正则别名" + annotation.patternAlias()+",请参考grape.common.rest.validation.ValidHelper.patternAliasMap");
                    }
                }
                if (isStrEmpty(pattern)) {
                    r = fieldValue != null;
                }
            }
            if(!r){
                validResult.setErrorMsg(annotation.message());
                return false;
            }
        }

        return true;
    }
}
