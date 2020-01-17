package grape.base.rest.user.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.application.mapper.ApplicationWebMapper;
import grape.base.rest.comp.mapper.CompWebMapper;
import grape.base.rest.dept.mapper.DeptWebMapper;
import grape.base.rest.func.mapper.FuncWebMapper;
import grape.base.rest.post.mapper.PostWebMapper;
import grape.base.rest.role.mapper.RoleWebMapper;
import grape.base.rest.user.form.*;
import grape.base.rest.user.mapper.UserWebMapper;
import grape.base.rest.user.vo.CurrentUserinfoVo;
import grape.base.rest.user.vo.UserVo;
import grape.base.rest.userpost.mapper.UserPostWebMapper;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.post.api.IPostService;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.po.User;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import grape.common.service.loginuser.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
public class UserController extends BaseLoginUserController<UserVo, User, LoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeUser");
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    @Autowired
    private ApplicationWebMapper applicationWebMapper;
    @Autowired
    private FuncWebMapper funcWebMapper;
    @Autowired
    private UserPostWebMapper userPostWebMapper;

    /**
     * 开启全局
     * @return
     */
    @Override
    public boolean isEnableDefaultDataObject() {
        // 判断是否存在关闭的情况
        if (getEnableDefaultDataObjectKeyValue() != null) {
            return (boolean) getEnableDefaultDataObjectKeyValue();
        }
        enableDefaultDataObject();
        return super.isEnableDefaultDataObject();
    }

    @Override
    protected String defaultDataObjectCode() {
        return defaultDataObjectCode.dataObjectCode();
    }

     @ApiOperation(value = "添加用户",notes = "添加用户的基本信息")
     @PreAuthorize("hasAuthority('user:single:create')")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo create(@RequestBody @Valid UserCreateForm cf) {
         UserCreateParam param =  userWebMapper.formToParam(cf);
         param.setPassword(passwordEncoder.encode(cf.getPwd()));
         UserVo vo = userWebMapper.poToVo(iUserService.createUser(param));
         return transVo(vo);
     }

     @ApiOperation(value = "查询用户",notes = "根据id查询用户")
     @PreAuthorize("hasAuthority('user:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation(value = "更新用户",notes = "更新用户基本信息")
     @PreAuthorize("hasAuthority('user:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserVo update(@PathVariable String id,@RequestBody @Valid UserUpdateForm uf) {
         User user = userWebMapper.formToPo(uf);
         user.setId(id);
         return super.update(user);
     }

    @ApiOperation("分页查询用户")
    @PreAuthorize("hasAuthority('user:single:listPage')")
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
    @PreAuthorize("hasAuthority('user:single:list')")
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
    @PreAuthorize("hasAuthority('user:single:enable')")
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

    /**
     * 后台管理用户登录成功后获取当前登录用户信息
     * @return
     */
    @ApiOperation("当前登录用户信息")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/userinfo/current")
    @ResponseStatus(HttpStatus.OK)
    public CurrentUserinfoVo currentUserinfo() {

        LoginUser loginUser = getLoginUser();

        CurrentUserinfoVo currentUserinfoVo = new CurrentUserinfoVo();
        // 基本信息
        currentUserinfoVo.setId(loginUser.getUserId());
        return currentUserinfoVo;
    }
}
