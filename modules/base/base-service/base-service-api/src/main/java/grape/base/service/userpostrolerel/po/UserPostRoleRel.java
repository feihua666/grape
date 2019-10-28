package grape.base.service.userpostrolerel.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.RelBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_post_role_rel")
public class UserPostRoleRel extends RelBasePo<UserPostRoleRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户岗位表id
     */
    private String userPostId;

    /**
     * 角色id
     */
    private String roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
