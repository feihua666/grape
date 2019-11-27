package grape.common.service.trans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

/**
 * 一般为vo层使用
 * 需要翻译的字段注解
 * 标注到需要翻译的字段上
 * Created by yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface TransBy {

    /**
     * 类型
     * TransService 的support方法参数
     * @return
     */
    String type();

    /**
     * 需要翻译的字段名称
     * 翻译进行时会到该字段名称的get方法的值作为TransService的key
     * @return
     */
    String byFieldName();
}
