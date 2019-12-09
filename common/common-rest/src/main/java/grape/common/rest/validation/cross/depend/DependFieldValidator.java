package grape.common.rest.validation.cross.depend;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import grape.common.rest.validation.ValidHelper;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
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
public class DependFieldValidator implements ConstraintValidator<DependField, Object>, ToolService {

    @Autowired
    private ValidHelper validHelper;
    private boolean dict;
    private String dependProp;
    private String targetProp;
    private String equal;
    private String pattern;
    private String patternAlias;
    private String message;
    private String reportOn;

    @Override
    public void initialize(DependField constraintAnnotation) {

        this.dict = constraintAnnotation.dict();
        this.dependProp = constraintAnnotation.dependProp();
        this.targetProp = constraintAnnotation.targetProp();
        this.equal = constraintAnnotation.ifEqual();
        this.pattern = constraintAnnotation.thenPattern();
        this.patternAlias = constraintAnnotation.patternAlias();
        this.message = constraintAnnotation.message();
        this.reportOn = constraintAnnotation.reportOn();
        this.reportOn = constraintAnnotation.reportOn();

        if(isStrEmpty(this.pattern) && !isStrEmpty(this.patternAlias)){

            this.pattern = validHelper.getPatternAliasMap().get(this.patternAlias);
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
            if(dependValue != null){
                Map<String, String> dictCodeMap = validHelper.getDictCodeMap(new String[]{this.dependProp}, value);
                dependValue = dictCodeMap.get(dependValue);
            }else {
                log.debug("依赖字段验证：requestId=[{}],字典属性[{}]值为空",RequestIdTool.getRequestId(),this.dependProp);
            }
        }else {
            log.debug("依赖字段验证：requestId=[{}],没有指定字典属性，将直接用dependProp对应的值",RequestIdTool.getRequestId());
        }
        log.debug("依赖字段验证：requestId=[{}],dependValue=[{}],ifEqual=[{}],targetValue=[{}]",
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
                validHelper.reportMessageOnProp(this.reportOn,this.message,constraintValidatorContext);
                return false;
            }
        }
        return true;
    }
}
