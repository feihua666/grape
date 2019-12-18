package grape.base.service.func.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.po.Func;
import grape.base.service.func.mapper.FuncMapper;
import grape.base.service.func.api.IFuncService;
import grape.base.service.postfuncrel.api.IPostFuncRelService;
import grape.base.service.postfuncrel.po.PostFuncRel;
import grape.base.service.rolefuncrel.api.IRoleFuncRelService;
import grape.base.service.rolefuncrel.po.RoleFuncRel;
import grape.base.service.userfuncrel.api.IUserFuncRelService;
import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.base.service.userpostfuncrel.api.IUserPostFuncRelService;
import grape.base.service.userpostfuncrel.po.UserPostFuncRel;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单功能表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class FuncServiceImpl extends BaseServiceImpl<FuncMapper, Func> implements IFuncService {


    @Autowired
    private IRoleFuncRelService iRoleFuncRelService;
    @Autowired
    private IUserFuncRelService iUserFuncRelService;
    @Autowired
    private IUserPostFuncRelService iUserPostFuncRelService;
    @Autowired
    private IPostFuncRelService iPostFuncRelService;

    @Autowired
    private IDictService iDictService;

    @Override
    public List<Func> getByRoleId(String roleId,Func funcQuery) {
        assertParamNotEmpty(roleId,"roleId 不能为空");
        List<RoleFuncRel> roleFuncRels = iRoleFuncRelService.getByRoleId(roleId);
        if (!isEmpty(roleFuncRels)) {
            Set<String> funcIds = roleFuncRels.stream().map(rel -> rel.getFuncId()).collect(Collectors.toSet());
            return listByIds(funcIds,funcQuery);
        }
        return null;
    }

    @Override
    public List<Func> getByUserId(String userId,Func funcQuery) {
        assertParamNotEmpty(userId,"userId 不能为空");
        List<UserFuncRel> userFuncRels = iUserFuncRelService.getByUserId(userId);
        if (!isEmpty(userFuncRels)) {
            Set<String> funcIds = userFuncRels.stream().map(rel -> rel.getFuncId()).collect(Collectors.toSet());
            return listByIds(funcIds,funcQuery);
        }
        return null;
    }

    @Override
    public List<Func> getByUserPostId(String userPostId, Func funcQuery) {
        assertParamNotEmpty(userPostId,"userPostId 不能为空");
        List<UserPostFuncRel> userPostFuncRels = iUserPostFuncRelService.getByUserPostId(userPostId);
        if (!isEmpty(userPostFuncRels)) {
            Set<String> funcIds = userPostFuncRels.stream().map(rel -> rel.getFuncId()).collect(Collectors.toSet());
            return listByIds(funcIds,funcQuery);
        }
        return null;
    }

    @Override
    public List<Func> getByPostId(String postId, Func funcQuery) {
        assertParamNotEmpty(postId,"postId 不能为空");
        List<PostFuncRel> postFuncRels = iPostFuncRelService.getByPostId(postId);
        if (!isEmpty(postFuncRels)) {
            Set<String> funcIds = postFuncRels.stream().map(rel -> rel.getFuncId()).collect(Collectors.toSet());
            return listByIds(funcIds,funcQuery);
        }
        return null;
    }

    @Override
    public List<Func> getByTypes(List<Func.TypeDictItem> dictItems, Func funcQuery) {
        assertNotEmpty(dictItems,"dictItems 不能为空");
        Set<String> dictItemsCode = dictItems.stream().map(dict-> dict.name()).collect(Collectors.toSet());
        List<Dict> list = iDictService.list(Wrappers.<Dict>lambdaQuery().in(Dict::getCode, dictItemsCode).eq(Dict::getIsDisabled,false));
        if (isEmpty(list)) {
            return null;
        }
        Set<String> dictIds = list.stream().map(dict-> dict.getId()).collect(Collectors.toSet());
        return list(Wrappers.<Func>lambdaQuery(funcQuery).in(Func::getTypeDictId,dictIds));
    }

}
