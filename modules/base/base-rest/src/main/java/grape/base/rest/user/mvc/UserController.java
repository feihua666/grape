package grape.base.rest.user.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.BaseRestSuperController;
import grape.base.rest.comp.mapper.CompWebMapper;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.form.LoginForm;
import grape.base.rest.user.form.UserCreateForm;
import grape.base.rest.user.form.UserListPageForm;
import grape.base.rest.user.form.UserUpdateForm;
import grape.base.rest.user.mapper.UserWebMapper;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.common.exception.ExceptionTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
public class UserController extends BaseRestSuperController<UserVo, User> {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserWebMapper userWebMapper;
    @Autowired
    private CompWebMapper compWebMapper;
    @Autowired
    private DeptWebMapper deptWebMapper;



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
        Dict genderDict = getIDictService().getById(loginVo.getIdentityTypeDictId());
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
        currentUserinfoVo.setId(loginUser.getUserId());
        currentUserinfoVo.setNickname(loginUser.getNickname());
        currentUserinfoVo.setAvatar(loginUser.getAvatar());

        currentUserinfoVo.setIsSwitchRole(loginUser.getIsSwitchRole());
        currentUserinfoVo.setCompId(loginUser.getComp().getId());
        currentUserinfoVo.setDeptId(loginUser.getDept().getId());
        if (loginUser.getCurrentRole() != null) {
            currentUserinfoVo.setRoleId(loginUser.getCurrentRole().getId());
        }
        if (loginUser.getCurrentUserPost() != null) {
            currentUserinfoVo.setPostId(loginUser.getCurrentUserPost().getPostId());
        }
        if (loginUser.getComp() != null) {
            currentUserinfoVo.setCompName(loginUser.getComp().getName());
        }
        if (loginUser.getDept() != null) {
            currentUserinfoVo.setDeptName(loginUser.getDept().getName());
        }
        return currentUserinfoVo;
    }





    /************************分割线，以下代码为 后台管理用户表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation(value = "添加用户",notes = "添加用户的基本信息，不包括登录帐号")
     @RequiresPermissions("user:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         User poQuery =  userWebMapper.formToPo(cf);
         poQuery.setCompId(getDeptById(cf.getDeptId()).getCompId());
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

    @ApiOperation("[后台管理用户表]单表分页列表")
    @RequiresPermissions("user:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserVo> listPage(UserListPageForm listPageForm) {
        User user = userWebMapper.formToPo(listPageForm);
         return super.listPage(user,listPageForm);
     }

    @Override
    public UserVo transVo(UserVo vo) {
        Dict genderDict = getDictById(vo.getGenderDictId());
        if (genderDict != null) {
            vo.setGenderDictCode(genderDict.getCode());
            vo.setGenderDictName(genderDict.getName());
        }
        Dept dept = getDeptById(vo.getDeptId());
        if (dept != null) {
            vo.setDeptName(dept.getName());
        }
        Comp comp = getCompById(vo.getCompId());
        if (comp != null) {
            vo.setCompName(comp.getName());
        }
        return vo;
    }
}
