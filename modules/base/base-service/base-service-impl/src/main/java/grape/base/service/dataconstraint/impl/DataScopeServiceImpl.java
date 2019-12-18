package grape.base.service.dataconstraint.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.base.service.postfuncrel.api.IPostFuncRelService;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.base.service.userdatascoperel.api.IUserDataScopeRelService;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据范围约束表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class DataScopeServiceImpl extends BaseServiceImpl<DataScopeMapper, DataScope> implements IDataScopeService {

    @Autowired
    private IUserDataScopeRelService iUserDataScopeRelService;
    @Autowired
    private IRoleDataScopeRelService iRoleDataScopeRelService;
    @Autowired
    private IUserPostDataScopeRelService iUserPostDataScopeRelService;
    @Autowired
    private IPostDataScopeRelService iPostDataScopeRelService;
    @Override
    public List<DataScope> getByUserId(String userId,DataScope query) {
        assertParamNotEmpty(userId,"userId 不能为空");
        List<UserDataScopeRel> userDataScopeRelList = iUserDataScopeRelService.getByUserId(userId);
        if (!isEmpty(userDataScopeRelList)) {
            Set<String> datascopeIds = userDataScopeRelList.stream().map(userDataScopeRel -> userDataScopeRel.getDataScopeId()).collect(Collectors.toSet());
            return listByIds(datascopeIds, query);
        }
        return null;
    }

    @Override
    public List<DataScope> getByRoleId(String roleId, DataScope query) {
        assertParamNotEmpty(roleId,"roleId 不能为空");
        List<RoleDataScopeRel> roleDataScopeRelList = iRoleDataScopeRelService.getByRoleId(roleId);
        if (!isEmpty(roleDataScopeRelList)) {
            Set<String> datascopeIds = roleDataScopeRelList.stream().map(userDataScopeRel -> userDataScopeRel.getDataScopeId()).collect(Collectors.toSet());
            return listByIds(datascopeIds, query);
        }
        return null;
    }

    @Override
    public List<DataScope> getByUserPostId(String userPostId, DataScope query) {
        assertParamNotEmpty(userPostId,"userPostId 不能为空");
        List<UserPostDataScopeRel> userPostDataScopeRelList = iUserPostDataScopeRelService.getByUserPostId(userPostId);
        if (!isEmpty(userPostDataScopeRelList)) {
            Set<String> datascopeIds = userPostDataScopeRelList.stream().map(userDataScopeRel -> userDataScopeRel.getDataScopeId()).collect(Collectors.toSet());
            return listByIds(datascopeIds, query);
        }
        return null;
    }

    @Override
    public List<DataScope> getByPostId(String postId, DataScope query) {
        assertParamNotEmpty(postId,"postId 不能为空");
        List<PostDataScopeRel> postDataScopeRelList = iPostDataScopeRelService.getByPostId(postId);
        if (!isEmpty(postDataScopeRelList)) {
            Set<String> datascopeIds = postDataScopeRelList.stream().map(userDataScopeRel -> userDataScopeRel.getDataScopeId()).collect(Collectors.toSet());
            return listByIds(datascopeIds, query);
        }
        return null;
    }
}
