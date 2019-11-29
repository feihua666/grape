package grape.common.rest.validation.cross.depend;

import grape.common.rest.validation.Script;
import grape.common.rest.validation.form.FormValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 虽然加了grape.common.rest.validation.form.FormValidator但感觉在有些场景下还是实现起来还是比较繁琐
 * 所以又加这个验证器，主要用来一个字段依赖另一个字段来判断
 * 该验证也支持字典id转编码判断
 * 用法参见：grape.base.rest.user.form.identifier.UserIdentifierCreateForm
 * Created by yangwei
 * Created at 2019/11/28 10:21
 */
@Target({ TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DependFieldValidator .class })
public @interface DependField {

    String message() default "表单验证不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // 字典属性
    boolean dict() default false;
    // 依赖哪个字段
    String dependProp();
    String targetProp();
    // 表示依赖的字段值与该值相等对则验证pattern
    String equal() default "";
    // 支持正则
    String pattern() default "";

    // 提供默认正则内置别名
    String patternAlias() default "";

    String reportOn() default "";

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        DependField[] value();
    }
}
