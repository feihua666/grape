package grape.base.service.user.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.user.po.UserIdentifier;
import grape.common.service.common.IBaseService;

/**
 * <p>
 * 用户登录标识表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IUserIdentifierService extends IBaseService<UserIdentifier> {

    default UserIdentifier getByIdentifier(String identifier){
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setIdentifier(identifier);
        return getOne(Wrappers.query(userIdentifier));
    }

}
