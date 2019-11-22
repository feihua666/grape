package grape.base.service.user.api;

import grape.base.service.BaseLoginUser;
import grape.base.service.user.po.User;
import grape.common.service.common.IBaseService;

/**
 * <p>
 * 后台管理用户表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 初始化当前登录用户信息用户信息
     * 一般登录成功后调用
     * @param identifier
     * @return
     */
    BaseLoginUser initLoginUserByIdentifier(String identifier);
}
