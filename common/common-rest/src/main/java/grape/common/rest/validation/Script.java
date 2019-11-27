package grape.common.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义脚本验证器，暂时主要是为了添加字典id转code判断
 * 跟ScriptAssert没啥区别，只是额外添加了内置函数getDictCodeById(id),mobile(xx),email(xx)
 * 如果脚本中使用字典方法请添加dictIdProp字典属性名称
 * Created by yangwei
 * Created at 2019/11/27 14:33
 */

@Documented
@Constraint(
        validatedBy = {ScriptValidator.class}
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Script.List.class)
public @interface Script {
    String message() default "{org.hibernate.validator.constraints.ScriptAssert.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String lang() default "javascript";

    String script();

    String alias() default "_this";

    String reportOn() default "";
    // 如果脚本中有调用字典转code请指定字典的属性
    String[] dictIdProp() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Script[] value();
    }
}
