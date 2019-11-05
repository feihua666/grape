package grape.base.service.role.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_role")
public class Role extends TreeBasePo<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 禁用原因
     */
    private String disabledReason;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
