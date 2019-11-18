package grape.base.rest.user.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.comp.mapper.CompWebMapper;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.post.mapper.PostWebMapper;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.form.*;
import grape.base.rest.user.mapper.UserWebMapper;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.post.api.IPostService;
import grape.base.service.post.po.Post;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.common.exception.ExceptionTools;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController extends BaseController<UserVo, User> {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserWebMapper userWebMapper;
    @Autowired
    private CompWebMapper compWebMapper;
    @Autowired
    private DeptWebMapper deptWebMapper;
    @Autowired
    private RoleWebMapper roleWebMapper;
    @Autowired
    private PostWebMapper postWebMapper;

    @Autowired
    private IDictService iDictService;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;
    @Autowired
    private IPostService iPostService;
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


     @ApiOperation(value = "添加用户",notes = "添加用户的基本信息，不包括登录帐号")
     @RequiresPermissions("user:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         User poQuery =  userWebMapper.formToPo(cf);
         poQuery.setCompId(iDeptService.getById(cf.getDeptId()).getCompId());
         User dbPo = getService().create(poQuery);
         if (dbPo == null) {
             throw ExceptionTools.newRE("添加失败");
         }
         UserVo vo = getMapperConverter().poToVo(dbPo);
         return vo;
     }

     @ApiOperation(value = "查询用户",notes = "根据id查询用户")
     @RequiresPermissions("user:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation(value = "更新用户",notes = "更新用户基本信息")
     @RequiresPermissions("user:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo update(@PathVariable String id,@RequestBody @Valid UserUpdateForm uf) {
         User user = userWebMapper.formToPo(uf);
         user.setId(id);
         return super.update(user);
     }

    @ApiOperation("分页查询用户")
    @RequiresPermissions("user:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserVo> listPage(UserListPageForm listPageForm) {
        User user = userWebMapper.formToPo(listPageForm);
         return super.listPage(user,listPageForm);
     }

    /**
     * 启用或锁定
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或锁定")
    @RequiresPermissions("user:single:enable")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo enable(@PathVariable String id, @RequestBody @Valid UserEnableForm form) {
        User user = new User();
        user.setId(id);
        user.setIsLock(form.getIsLock());
        user.setVersion(form.getVersion());
        user.setLockReason(form.getLockReason());
        return super.update(user);
    }

    @Override
    public UserVo transVo(UserVo vo) {
        Dict genderDict = iDictService.getById(vo.getGenderDictId());
        if (genderDict != null) {
            vo.setGenderDictCode(genderDict.getCode());
            vo.setGenderDictName(genderDict.getName());
        }
        Dept dept = iDeptService.getById(vo.getDeptId());
        if (dept != null) {
            vo.setDeptName(dept.getName());
        }
        Comp comp = iCompService.getById(vo.getCompId());
        if (comp != null) {
            vo.setCompName(comp.getName());
        }
        return vo;
    }
}
