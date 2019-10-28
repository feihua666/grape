package grape.base.service.dict.po;

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
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用
 * </p>
 *
 * @author yangwei
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_dict")
public class Dict extends TreeBasePo<Dict> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据值
     */
    private String code;

    /**
     * 标签名
     */
    private String name;

    /**
     * 是否为系统字典，一般系统字典代码中会做判断，不能修改或删除
     */
    private Boolean isSystem;

    /**
     * 是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询
     */
    private Boolean isPublic;

    /**
     * 是否为字典组，不是字典组就是字典项目，没有其它的
     */
    private Boolean isGroup;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 禁用原因
     */
    private String disabledReason;

    /**
     * 公司id，标识字典归属于哪个公司
     */
    private String compId;

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
