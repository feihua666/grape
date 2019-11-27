package grape.common.service.trans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一般为vo层使用
 * 标注到类上
 * 可以单独使用或结合grape.common.service.trans.Trans使用
 * Created by yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TransItem {

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
    /**
     * 需要被翻译的字段名称
     * 翻译进行时会到标注于字段名称的get方法的值作为TransService的key
     * forFieldName 是目标字段名
     * @return
     */
    String forFieldName();
}
