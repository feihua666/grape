package grape.base.service.rolefuncrel.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.RelBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单功能关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_role_func_rel")
public class RoleFuncRel extends RelBasePo<RoleFuncRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 用户id
     */
    private String funcId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
