package grape.base.service.userrolerel.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.RelBasePo;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单功能关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_user_role_rel")
public class UserRoleRel extends RelBasePo<UserRoleRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
