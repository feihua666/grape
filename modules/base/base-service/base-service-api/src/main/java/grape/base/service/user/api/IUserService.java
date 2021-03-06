package grape.base.service.user.api;

import grape.base.service.BaseLoginUser;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.po.User;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 后台管理用户表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IUserService extends IBaseService<User>, ITransService<String, String> {

    public static final String trans_type_userNickname ="userNickname";
    /**
     * 添加用户
     * @param cp
     */

    User createUser(UserCreateParam cp);

    @Override
    default boolean support(String type){
        return isEqual(type, trans_type_userNickname);
    }

    @Override
    default String trans(String type, String key){
        User user = getById(key);
        if (user != null) {
            return user.getNickname();
        }
        return null;
    }
}
