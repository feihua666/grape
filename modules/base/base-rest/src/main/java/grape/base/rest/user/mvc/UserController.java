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
import grape.base.rest.user.form.login.LoginForm;
import grape.base.rest.user.mapper.UserWebMapper;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.LoginVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.service.BaseLoginUser;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.post.api.IPostService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.po.User;
import grape.common.exception.ExceptionTools;
import grape.common.rest.common.PasswordAndSalt;
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


     @ApiOperation(value = "添加用户",notes = "添加用户的基本信息，不包括登录帐号")
     @RequiresPermissions("user:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         PasswordAndSalt newPs = PasswordAndSalt.entryptPassword(cf.getPwd());
         UserCreateParam param =  userWebMapper.formToParam(cf);
         param.setPassword(newPs.getPassword());
         param.setSalt(newPs.getSalt());

         UserVo vo = userWebMapper.poToVo(iUserService.createUser(param));
         return transVo(vo);
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
     * 主要用于下拉搜索
     * @param listPageForm
     * @return
     */
    @ApiOperation(value = "不分页查询用户")
    @RequiresPermissions("user:single:list")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserVo> list(UserListForm listForm) {
        User user = userWebMapper.formToPo(listForm);
        return super.list(user);
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
}
