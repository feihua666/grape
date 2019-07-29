package grape.common.service.po;

import java.lang.annotation.*;

/**
 * mybatis plus 自动填充扩展注解
 * 提供自动填充默认值
 * 可用变量[now]=当前时间，[userId}=当前登录用户的id
 * Created by yangwei
 * Created at 2019/7/28 16:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldFillDefault {
    // 可用变量
    // 当前时间
    public static final String VAR_NOW = "[now]";
    // 当前登录用户id
    public static final String VAR_USERID = "[userId]";

    // 为空表示不填充
    String insert() default "";
    String update() default "";
}
