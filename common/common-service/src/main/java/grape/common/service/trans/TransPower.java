package grape.common.service.trans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加速翻译处理
 *
 *
 * Created by yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TransPower {
}
