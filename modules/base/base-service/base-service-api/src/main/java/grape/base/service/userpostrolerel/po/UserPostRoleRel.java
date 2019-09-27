package grape.base.service.userpostrolerel.po;

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
    private Long userPostId;

    /**
     * 角色id
     */
    private Long roleId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}