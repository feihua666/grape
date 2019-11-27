package grape.common.service.trans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一般为vo层使用
 * 需要翻译的字段注解
 * 标注到需要被翻译的主字段上，如：要翻译用户名，则需要标注在用户id字段上，主要是为了解决继承关系父类中存在的需要翻译的字段
 * Created by yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface TransFor {

    /**
     * 类型
     * TransService 的support方法参数
     * @return
     */
    String type();

    /**
     * 需要被翻译的字段名称
     * 翻译进行时会到标注于字段名称的get方法的值作为TransService的key
     * forFieldName 是目标字段名
     * @return
     */
    String forFieldName();
}
