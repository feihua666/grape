package grape.base.service.userpostrolerel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.userpostrolerel.po.UserPostRoleRel;
import grape.common.service.common.IBaseService;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IUserPostRoleRelService extends IBaseService<UserPostRoleRel> {

    default UserPostRoleRel getByUserPostId(String userPostId){
        if (userPostId == null) {
            return null;
        }
        UserPostRoleRel userPostRoleRel = new UserPostRoleRel();
        userPostRoleRel.setUserPostId(userPostId);
        return getOne(Wrappers.query(userPostRoleRel));
    }
}
