package grape.auth.rest.user.mvc;

import grape.auth.rest.user.UserLoginHelperService;
import grape.auth.rest.user.form.login.LoginForm;
import grape.auth.rest.user.vo.LoginVo;
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
import grape.common.service.loginuser.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by yangwei
 * Created at 2019/11/28 14:22
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录及当前登录用户相关接口")
public class UserLoginController extends SuperController {

    @Autowired
    private UserLoginHelperService loginUserHelperService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IDataObjectService dataObjectService;

    @Autowired
    private IUserPwdService userPwdService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private IUserIdentifierService userIdentifierService;

    /**
     * 后台管理用户登录入口
     * @param loginForm
     * @return
     */
    @ApiOperation(value = "登陆",notes = "后台管理用户登陆入口")
    @PostMapping(value = "login")
    @ResponseStatus(HttpStatus.OK)
    public LoginVo login(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication =  authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginVo loginVo = new LoginVo();
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String jwtToken = jwtHelper.createTokenWithUserId(loginUser.getUserId());
        loginVo.setToken(jwtToken);
        return loginVo;
    }

    /**
     * 后台管理用户退出登陆
     * @return
     */
    @ApiOperation(value = "退出登陆",notes = "后台管理用户退出登陆")
    @PostMapping(value = "logout")
    @ResponseStatus(HttpStatus.OK)
    public Boolean logout() {
        SecurityContextHolder.clearContext();
        return true;
    }

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
