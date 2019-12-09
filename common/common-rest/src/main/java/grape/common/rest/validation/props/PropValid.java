package grape.common.rest.validation.props;

import grape.common.rest.validation.form.FormValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * 属性验证
 * 区别于grape.common.rest.validation.cross.depend.DependField是标注在类上
 * 该PropValid也标注在类上，但中为了进入验证器，配置子验证注解标注在字段上使用
 * Created by yangwei
 * Created at 2019/11/28 8:54
 */
@Target({ TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PropValidValidator.class })
public @interface PropValid {

    String message() default "表单验证不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    // 如果需要字典转code请指定字典的属性
    String[] dictIdProp() default {};
    /**
     * 标注在字段上，表示依赖另一个字段，如果另一个字段有值，那么该字段也得有值
     */
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Depend {
        String message();

        // 依赖的属性名
        String dependProp();
    }
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface DependList {
        Depend [] value();
    }

    /**
     * 表示依赖字典
     */
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface DependCondition {
        String message();

        // 依赖的属性名
        String dependProp();
        // 依赖的字段是否为字典id
        boolean dependDict() default false;
        // 依赖的字段是否等于该值
        String ifEqual() default "";
        // 支持正则，如果没有指定则判断不为空
        String thenPattern() default "";

        // 提供默认正则内置别名
        String patternAlias() default "";
    }
    /**
     * 表示依赖字典多个
     */
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface DependConditionList {
        DependCondition[] value();
    }
}
