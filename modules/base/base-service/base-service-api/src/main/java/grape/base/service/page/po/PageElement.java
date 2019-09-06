package grape.base.service.page.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.TreeBasePo;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_page_element")
public class PageElement extends TreeBasePo<PageElement> {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编码，用于用程序识别
     */
    private String code;

    /**
     * 页面名称
     */
    private String name;

    /**
     * 页面元素类型
     */
    private Long typeDictId;

    /**
     * 元素为表单项或表格列有效，字段名称
     */
    private String fieldName;

    /**
     * 元素为表单项或表格列有效，字段默认值
     */
    private String fieldDefaultValue;

    /**
     * 元素为表单项或表格列有效，字段类型，标识是字符串还是数据字
     */
    private Long fieldTypeDictId;

    /**
     * 数据库字段名
     */
    private String columnName;

    /**
     * 页面id，标识元素属于哪个页面
     */
    private Long pageId;

    /**
     * 描述
     */
    private String remark;

    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
