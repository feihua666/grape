package grape.base.service.user.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.user.po.UserIdentifier;
import grape.common.service.common.IBaseService;

/**
 * <p>
 * 用户登录帐号表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IUserIdentifierService extends IBaseService<UserIdentifier> {

    default UserIdentifier getByIdentifier(String identifier){
        assertParamNotEmpty(identifier,"identifier不能为空");

        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setIdentifier(identifier);
        return getOne(Wrappers.query(userIdentifier));
    }

    UserIdentifier getByUserIdAndType(String userId,String type);
    UserIdentifier getByUserIdAndTypeDictId(String userId,String typeDictId);
}
