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
 * 页面表，用于自动生成页面元素和关联功能菜单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_page")
public class PagePo extends TreeBasePo<PagePo> {

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
     * 描述
     */
    private String desc;

    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
