package grape.base.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.mapper.UserIdentifierMapper;
import grape.base.service.user.api.IUserIdentifierService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录帐号表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class UserIdentifierServiceImpl extends BaseServiceImpl<UserIdentifierMapper, UserIdentifier> implements IUserIdentifierService {

    @Autowired
    private IDictService iDictService;
    @Override
    public UserIdentifier getByUserIdAndType(String userId, String type) {

        assertParamNotEmpty(type,"type不能为空");
        Dict dict = iDictService.getByCode(type);
        if (dict == null) {
            return null;
        }
        return getByUserIdAndTypeDictId(userId,dict.getId());
    }

    @Override
    public UserIdentifier getByUserIdAndTypeDictId(String userId, String typeDictId) {
        assertParamNotEmpty(userId,"userId不能为空");
        assertParamNotEmpty(typeDictId,"typeDictId不能为空");
        UserIdentifier userIdentifier = new UserIdentifier();
        userIdentifier.setUserId(userId);
        userIdentifier.setIdentityTypeDictId(typeDictId);
        return getOne(Wrappers.query(userIdentifier));
    }
}
