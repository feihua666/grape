package grape.common.rest.advice;

import java.lang.annotation.*;

/**
 * 不使用统一返回响应体的禁用注解
 * 增加自由性，旨在一些第三方接口不适应统一响应时使用
 * Created by yangwei
 * Created at 2019/7/26 19:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisabledGRM {
}
