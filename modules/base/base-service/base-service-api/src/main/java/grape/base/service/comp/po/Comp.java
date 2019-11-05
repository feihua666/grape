package grape.base.service.comp.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_comp")
public class Comp extends TreeBasePo<Comp> {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
    private String code;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 类型,字典id
     */
    private String typeDictId;

    /**
     * 负责人用户id，该id可用来填充审批人
     */
    private String masterUserId;

    /**
     * 是否虚拟公司
     */
    private Boolean isVirtual;

    /**
     * 描述、备注
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
