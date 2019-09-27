package grape.base.rest.user.mvc;

import grape.base.rest.setting.shiro.IdentifierPasswordToken;
import grape.base.rest.user.form.LoginForm;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.po.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.user.form.UserCreateForm;
import grape.base.rest.user.form.UserUpdateForm;
import grape.base.rest.user.form.UserListPageForm;
import grape.base.rest.user.vo.UserVo;
import grape.base.rest.user.mapper.UserWebMapper;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.user.api.IUserService;

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
public class UserController extends BaseController<IUserService,UserWebMapper, UserVo, User, UserCreateForm,UserUpdateForm,UserListPageForm> {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IDictService iDictService;



    // 请在这里添加额外的方法
    //todo

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
        Dict dict = iDictService.getById(loginUser.getUserIdentifier().getIdentityTypeDictId());
        loginVo.setUserId(loginUser.getUserId());
        loginVo.setIdentifierType(dict.getCode());
        loginVo.setIdentifierTypeTxt(dict.getName());
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
        return currentUserinfoVo;
    }





    /************************分割线，以下代码为 后台管理用户表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("[后台管理用户表]单表创建/添加数据")
     @RequiresPermissions("user:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         return super.create(cf);
     }

     @ApiOperation("[后台管理用户表]单表根据ID查询")
     @RequiresPermissions("user:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserVo queryById(@PathVariable Long id) {
         return super.queryById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID删除")
     @RequiresPermissions("user:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable Long id) {
         return super.deleteById(id);
     }

     @ApiOperation("[后台管理用户表]单表根据ID更新")
     @RequiresPermissions("user:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo update(@PathVariable Long id,@RequestBody @Valid UserUpdateForm uf) {
         return super.update(id, uf);
     }

    @ApiOperation("[后台管理用户表]单表分页列表")
    @RequiresPermissions("user:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserVo> listPage(UserListPageForm listPageForm) {
         return super.listPage(listPageForm);
     }
}
