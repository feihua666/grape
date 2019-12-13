package grape.base.rest.user.mvc;

import grape.base.rest.application.mapper.ApplicationWebMapper;
import grape.base.rest.application.vo.ApplicationVo;
import grape.base.rest.post.mapper.PostWebMapper;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.setting.shiro.BaseDbRealm;
import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.form.login.LoginForm;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.application.api.IApplicationService;
import grape.base.service.application.po.Application;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.base.service.role.api.IRoleService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/11/28 14:22
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录及当前登录用户相关接口")
public class UserLoginController extends BaseController<UserVo, User> {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private RoleWebMapper roleWebMapper;
    @Autowired
    private PostWebMapper postWebMapper;

    @Autowired
    private IDictService iDictService;

    @Autowired
    private ApplicationWebMapper applicationWebMapper;

    @Autowired
    BaseDbRealm baseDbRealm;

    /**
     * 后台管理用户登录入口
     * @param loginForm
     * @return
     */
    @ApiOperation(value = "登陆",notes = "后台管理用户登陆入口")
    @PostMapping(value = "login")
    @ResponseStatus(HttpStatus.OK)
    public LoginVo login(@RequestBody @Valid LoginForm loginForm) {
        SecurityUtils.getSubject().login(new IdentifierPasswordToken(loginForm.getUsername(),loginForm.getPassword()));

        // 登录成功初始化用户信息
        BaseLoginUser loginUser = iUserService.initLoginUserByIdentifier(loginForm.getUsername());
        BaseLoginUser.setLoginUser(loginUser);
        // 同时放到session中
        SecurityUtils.getSubject().getSession().setAttribute(BaseLoginUser.loginUserKey,loginUser);
        LoginVo loginVo = new LoginVo();
        loginVo.setId(loginUser.getUserId());
        loginVo.setIdentityTypeDictId(loginUser.getUserIdentifier().getIdentityTypeDictId());
        Dict genderDict = iDictService.getById(loginVo.getIdentityTypeDictId());
        if (genderDict != null) {
            loginVo.setIdentityTypeDictCode(genderDict.getCode());
            loginVo.setIdentityTypeDictName(genderDict.getName());
        }
        return loginVo;
    }

    /**
     * 后台管理用户登录成功后获取当前登录用户信息
     * @return
     */
    @ApiOperation("当前登录用户信息")
    @RequiresPermissions("user")
    @GetMapping("/userinfo/current")
    @ResponseStatus(HttpStatus.OK)
    public CurrentUserinfoVo currentUserinfo() {
        BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
        CurrentUserinfoVo currentUserinfoVo = new CurrentUserinfoVo();
        // 基本信息
        currentUserinfoVo.setId(loginUser.getUserId());
        currentUserinfoVo.setNickname(loginUser.getNickname());
        currentUserinfoVo.setAvatar(loginUser.getAvatar());

        // 角色或岗位切换模式
        currentUserinfoVo.setIsSwitchRole(loginUser.getIsSwitchRole());

        // 用户归属的公司
        if (loginUser.getComp() != null) {
            currentUserinfoVo.setCompId(loginUser.getComp().getId());
            currentUserinfoVo.setCompName(loginUser.getComp().getName());
        }
        // 用户归属的部门
        if (loginUser.getDept() != null) {
            currentUserinfoVo.setDeptId(loginUser.getDept().getId());
            currentUserinfoVo.setDeptName(loginUser.getDept().getName());
        }
        // 当前的角色
        if (loginUser.getCurrentRole() != null) {
            currentUserinfoVo.setCurrentRoleId(loginUser.getCurrentRole().getId());
            currentUserinfoVo.setCurrentRoleCode(loginUser.getCurrentRole().getCode());
            currentUserinfoVo.setCurrentRoleName(loginUser.getCurrentRole().getName());
        }
        // 当前使用的岗位关系
        if (loginUser.getCurrentUserPost() != null) {
            currentUserinfoVo.setCurrentPostId(loginUser.getCurrentUserPost().getPostId());
            currentUserinfoVo.setCurrentPostCode(loginUser.getCurrentUserPostInfo().getPost().getCode());
            currentUserinfoVo.setCurrentPostName(loginUser.getCurrentUserPostInfo().getPost().getName());
        }
        // 可切换的角色们
        if (!isListEmpty(loginUser.getRoles())) {
            List<RoleVo> roleVoList = loginUser.getRoles().stream().map(role -> roleWebMapper.poToVo(role)).collect(Collectors.toList());
            currentUserinfoVo.setRoles(roleVoList);
        }
        // 可切换的岗位们
        if(!isListEmpty(loginUser.getUserPostInfos())){
            List<PostVo> postVoList = loginUser.getUserPostInfos().stream().map(userPostInfo -> postWebMapper.poToVo(userPostInfo.getPost())).collect(Collectors.toList());
            currentUserinfoVo.setPosts(postVoList);
        }

        // 可用的应用们
        List<Application> applications = loginUser.getApplications();

        if (!isListEmpty(applications)) {
            List<ApplicationVo> applicationVos = applications.stream().map(application -> applicationWebMapper.poToVo(application)).collect(Collectors.toList());
            currentUserinfoVo.setApplications(applicationVos);
        }
        return currentUserinfoVo;
    }
    /**
     * 后台管理用户退出登陆
     * @return
     */
    @ApiOperation(value = "退出登陆",notes = "后台管理用户退出登陆")
    @PostMapping(value = "logout")
    @ResponseStatus(HttpStatus.OK)
    public Boolean logout() {
        SecurityUtils.getSubject().logout();
        return true;
    }

    /**
     * 后台管理用户切换角色
     * @param roleId 要切换的角色id
     * @return
     */
    @ApiOperation(value = "切换角色",notes = "后台管理用户切换角色")
    @PostMapping(value = "switchrole/{roleId}")
    @RequiresPermissions("user")
    @ResponseStatus(HttpStatus.OK)
    public Boolean switchRole(@PathVariable String roleId) {
        BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
        if (loginUser.getCurrentRole() == null) {
            throw new RBaseException("当前正在使用的角色为空");
        }
        if (isEqual(roleId,loginUser.getCurrentRole().getId())) {
            throw new InvalidParamsException("正在切换的角色为当前使用的角色，无需切换");
        }

        if(loginUser.getIsSwitchRole() == null){
            throw new RBaseException("当前不支持角色切换");
        }
        if(!loginUser.getIsSwitchRole()){
            throw new RBaseException("当前不支持角色切换，请尝试岗位切换");
        }
        swicth(roleId, null);
        return true;
    }
    /**
     * 后台管理用户切换角色
     * @param postId 要切换的岗位id
     * @return
     */
    @ApiOperation(value = "切换岗位",notes = "后台管理用户切换岗位")
    @RequiresPermissions("user")
    @PostMapping(value = "switchpost/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean switchPost(@PathVariable String postId) {
        BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
        if (loginUser.getCurrentUserPost() == null) {
            throw new RBaseException("当前正在使用的岗位为空");
        }
        if (isEqual(postId,loginUser.getCurrentUserPost().getPostId())) {
            throw new InvalidParamsException("正在切换的岗位为当前使用的岗位，无需切换");
        }
        if(loginUser.getIsSwitchRole() == null){
            throw new RBaseException("当前不支持岗位切换");
        }
        if(loginUser.getIsSwitchRole()){
            throw new RBaseException("当前不支持岗位切换,请尝试角色切换");
        }
        swicth(null, postId);
        return true;
    }

    /**
     * 切换,一次只能切换一个
     * @param roleId 角色id
     * @param postId 岗位id
     */
    private void swicth(String roleId,String postId){
        BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
        BaseLoginUser loginUserForInit = iUserService.initLoginUserByUserId(loginUser.getUserId(), loginUser.getUserIdentifier().getId(), roleId, postId);
        // 同时放到session中
        SecurityUtils.getSubject().getSession().setAttribute(BaseLoginUser.loginUserKey,loginUserForInit);
        // 重置当前用户
        BaseLoginUser.setLoginUser(loginUserForInit);
        // 清除授权缓存
        baseDbRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipal());
    }
}
