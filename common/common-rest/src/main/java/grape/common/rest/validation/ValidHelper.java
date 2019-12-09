package grape.common.rest.validation;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReflectUtil;
import grape.common.service.common.DictService;
import grape.common.tools.ToolService;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/12/6 10:18
 */
@Component
public class ValidHelper implements ToolService {
    @Autowired(required = false)
    private DictService dictService;


    public static final String pattern_alias_mobile = "mobile";
    public static final String pattern_alias_email = "email";

    private static Map<String,String> patternAliasMap = null;
    private static List<String> patternAliasScript = null;
    static {
        patternAliasMap = new HashMap<>(2);
        patternAliasMap.put(pattern_alias_mobile, PatternPool.MOBILE.pattern());
        patternAliasMap.put(pattern_alias_email, PatternPool.EMAIL.pattern());

        patternAliasScript = new ArrayList<>(patternAliasMap.size());
        patternAliasMap.forEach((key,pattern) -> {
            patternAliasScript.add("var " + key + " = function (value){return !value? true: new RegExp(/"+pattern+"/).test(value)};");
        });
    }
    /**
     * 添加错误消息到字段上
     * @param reportOn
     * @param message
     * @param constraintValidatorContext
     */
    public void reportMessageOnProp(String reportOn, String message, ConstraintValidatorContext constraintValidatorContext){
        if(!isStrEmpty(reportOn) && !isStrEmpty(message)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(reportOn).addConstraintViolation();
        }
    }

    /**
     * 字典属性id转编码
     * @param dictIdProp
     * @param value
     * @return
     */
    public Map<String,String> getDictCodeMap(String dictIdProp[],Object value){

        Map<String, String> dictCodeObj = CollectionHelper.newHashMap();
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
        return dictCodeObj;
    }

    /**
     * 正则别名
     * @return
     */
    public Map<String,String> getPatternAliasMap(){
        return patternAliasMap;
    }
    /**
     * 正则别名为函数名的验证脚本
     * @return
     */
    public List<String> getPatternAliasScript (){
        return patternAliasScript;

    };
}
