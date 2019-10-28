package grape.base.service.user.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.user.po.UserPwd;
import grape.common.service.IBaseService;

/**
 * <p>
 * 用户密码表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IUserPwdService extends IBaseService<UserPwd> {

    default UserPwd getByUserId(String userId){
        UserPwd userPwd = new UserPwd();
        userPwd.setUserId(userId);
        return getOne(Wrappers.query(userPwd));
    }
}
