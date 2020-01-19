package grape.base.rest.user.mvc;

import grape.base.rest.user.UserLoginHelperService;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.func.po.Func;
import grape.base.service.role.po.Role;
import grape.base.service.user.api.IUserIdentifierService;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.po.UserIdentifier;
import grape.base.service.user.po.UserPwd;
import grape.common.rest.advice.DisableGRB;
import grape.common.rest.advice.DisableGRM;
import grape.common.rest.mvc.SuperController;
import grape.common.rest.security.JwtHelper;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.DefaultDataScope;
import grape.common.service.common.dataconstraint.RawDataConstraint;
import grape.common.tools.ToolService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 仅供内部微服务调用
 * Created by yangwei
 * Created at 2020/1/19 17:19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "仅供内部微服务调用")
public class UserInnerController extends SuperController implements ToolService {

    @Autowired
    private UserLoginHelperService loginUserHelperService;
    @Autowired
    private IDataObjectService dataObjectService;

    @Autowired
    private IUserPwdService userPwdService;


    @Autowired
    private IUserIdentifierService userIdentifierService;

    /**
     * 内部调用接口获取用户的角色
     * @param userId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/roles")
    public List<String> getRolesCodeByUserId(String userId){
        List<Role> roles = loginUserHelperService.getRolesByUserId(userId);

        List<String> roleCodeList = new ArrayList<>();
        if (!isEmpty(roles)) {
            List<String> collect = roles.stream().map(role -> role.getCode()).collect(Collectors.toList());
            roleCodeList.addAll(collect);
        }
        return roleCodeList;
    }
    /**
     * 内部调用接口获取用户的功能
     * @param userId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/funcs")
    public List<String> getFuncsCodeByUserId(String userId){
        List<Func> funcs = loginUserHelperService.getFuncsByUserId(userId);
        List<String> roleCodeList = new ArrayList<>();
        if (!isEmpty(funcs)) {
            List<String> collect = funcs.stream().map(role -> role.getCode()).collect(Collectors.toList());
            roleCodeList.addAll(collect);
        }
        return roleCodeList;
    }
    /**
     * 内部调用接口获取用户的数据范围约束
     * @param userId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/dataScopes")
    public List<RawDataConstraint> getDataScopesByUserId(String userId){
        List<DataScope> dataScopes = loginUserHelperService.getDataScopesByUserId(userId);
        List<RawDataConstraint> rawDataConstraints = new ArrayList<>();
        RawDataConstraint rawDataConstraint = null;
        DefaultDataObject iDataObject = null;
        List<DefaultDataScope> iDataScopes = null;
        DefaultDataScope  defaultDataScope = null;
        if (!isEmpty(dataScopes)) {
            Map<String,List<DataScope>> map = new HashMap<>();
            for (DataScope dataScope : dataScopes) {

                List<DataScope> dataScopeList = map.get(dataScope.getDataObjectId());
                if (isEmpty(dataScopeList)) {
                    dataScopeList = new ArrayList<>();
                    map.put(dataScope.getDataObjectId(), dataScopeList);
                }
                dataScopeList.add(dataScope);

            }
            for (Map.Entry<String, List<DataScope>> stringListEntry : map.entrySet()) {

                DataObject dataObject = dataObjectService.getById(stringListEntry.getKey());
                iDataObject = new DefaultDataObject(dataObject.getCode(), dataObject.getId());
                iDataScopes = new ArrayList<>(stringListEntry.getValue().size());
                for (DataScope dataScope : stringListEntry.getValue()) {
                    defaultDataScope = new DefaultDataScope();
                    defaultDataScope.setCustom(dataScope.getIsCustom());
                    defaultDataScope.setId(dataScope.getId());
                    defaultDataScope.setDataScopeCode(dataScope.getCode());
                    defaultDataScope.setConstraintContent(dataScope.getConstraintContent());
                    iDataScopes.add(defaultDataScope);
                }
                rawDataConstraint = new RawDataConstraint(iDataObject, iDataScopes);
                rawDataConstraints.add(rawDataConstraint);
            }
        }
        return rawDataConstraints;
    }
    /**
     * 内部调用接口获取用户密码
     * @param userId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/pwd/userid")
    public String getPwdByUserId(String userId){
        String pwd = "";
        UserPwd byUserId = userPwdService.getByUserId(userId);
        if (byUserId != null) {
            pwd = byUserId.getPwd();
        }
        return pwd;
    }

    /**
     * 内部调用接口获取用户密码
     * @param identifier
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/pwd/identifier")
    public String getPwdByIdentifier(String identifier){
        String pwd = "";
        UserIdentifier userIdentifier = userIdentifierService.getByIdentifier(identifier);
        if (userIdentifier != null) {
            pwd = getPwdByUserId(userIdentifier.getUserId());
        }
        return pwd;
    }

    /**
     * 内部调用接口获取用户密码
     * @param identifierId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/pwd/identifierid")
    public String getPwdByIdentifierId(String identifierId){
        String pwd = "";
        UserIdentifier userIdentifier = userIdentifierService.getById(identifierId);
        if (userIdentifier != null) {
            pwd = getPwdByUserId(userIdentifier.getUserId());
        }
        return pwd;
    }

    /**
     * 内部调用接口获取用户的应用id
     * @param userId
     * @return
     */
    @DisableGRM
    @DisableGRB
    @GetMapping(value = "inner/applicationids")
    public List<String> getApplicationIdsByUserId(String userId){
        List<Func> funcList = loginUserHelperService.getFuncsByUserId(userId);
        if (!isEmpty(funcList)) {
            return funcList.stream().map(func -> func.getApplicationId()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
