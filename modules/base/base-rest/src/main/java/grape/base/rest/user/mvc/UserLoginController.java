package grape.base.rest.user.mvc;

import grape.base.rest.application.mapper.ApplicationWebMapper;
import grape.base.rest.application.vo.ApplicationVo;
import grape.base.rest.func.mapper.FuncWebMapper;
import grape.base.rest.func.vo.FuncVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.LoginUserHelperService;
import grape.base.rest.user.form.login.LoginForm;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.rest.userpost.mapper.UserPostWebMapper;
import grape.base.rest.userpost.vo.UserPostVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.application.po.Application;
import grape.base.service.func.po.Func;
import grape.base.service.user.po.User;
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
    private RoleWebMapper roleWebMapper;
    @Autowired
    private ApplicationWebMapper applicationWebMapper;
    @Autowired
    private FuncWebMapper funcWebMapper;
    @Autowired
    private UserPostWebMapper userPostWebMapper;

    @Autowired
    private LoginUserHelperService loginUserHelperService;

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
        BaseLoginUser loginUser = loginUserHelperService.initLoginUserByIdentifier(loginForm.getUsername());
        BaseLoginUser.setLoginUser(loginUser);
        // 同时放到session中
        SecurityUtils.getSubject().getSession().setAttribute(BaseLoginUser.loginUserKey,loginUser);
        LoginVo loginVo = new LoginVo();
        loginVo.setId(loginUser.getUserId());
        loginVo.setIdentityTypeDictId(loginUser.getUserIdentifier().getIdentityTypeDictId());
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
        currentUserinfoVo.setIsSuperAdmin(loginUser.getIsSuperAdmin());
        // 基本信息
        currentUserinfoVo.setId(loginUser.getUserId());
        currentUserinfoVo.setNickname(loginUser.getNickname());
        currentUserinfoVo.setAvatar(loginUser.getAvatar());

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

        // 分配的角色们
        if (!isEmpty(loginUser.getRoles())) {
            List<RoleVo> roleVoList = loginUser.getRoles().stream().map(role -> roleWebMapper.poToVo(role)).collect(Collectors.toList());
            currentUserinfoVo.setRoles(roleVoList);
        }
        // 分配的岗位们
        if (!isEmpty(loginUser.getUserPosts())) {
            List<UserPostVo> userPostVos = loginUser.getUserPosts().stream().map(userPost -> userPostWebMapper.poToVo(userPost)).collect(Collectors.toList());
            currentUserinfoVo.setUserPosts(userPostVos);
        }

        // 可用的应用们
        List<Application> applications = loginUser.getApplications();
        if (!isEmpty(applications)) {
            List<ApplicationVo> applicationVos = applications.stream().map(application -> applicationWebMapper.poToVo(application)).collect(Collectors.toList());
            currentUserinfoVo.setApplications(applicationVos);
        }
        // 可用的功能们
        List<Func> funcs = loginUser.getFuncs();
        if (!isEmpty(funcs)) {
            List<FuncVo> funcVos = funcs.stream().map(func -> funcWebMapper.poToVo(func)).collect(Collectors.toList());
            currentUserinfoVo.setFuncs(funcVos);
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
}
