package grape.base.rest.user.mvc;

import grape.base.rest.post.mapper.PostWebMapper;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.form.login.LoginForm;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.api.IUserService;
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
    private IUserService iUserService;
    @Autowired
    private RoleWebMapper roleWebMapper;
    @Autowired
    private PostWebMapper postWebMapper;

    @Autowired
    private IDictService iDictService;

    /**
     * 后台管理用户登录入口
     * @param loginForm
     * @return
     */
    @ApiOperation(value = "登录",notes = "后台管理用户登录入口")
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

        return currentUserinfoVo;
    }

}
