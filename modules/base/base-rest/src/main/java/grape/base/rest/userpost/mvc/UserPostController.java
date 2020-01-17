package grape.base.rest.userpost.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.userpost.form.UserPostCreateForm;
import grape.base.rest.userpost.form.UserPostEffectForm;
import grape.base.rest.userpost.form.UserPostListPageForm;
import grape.base.rest.userpost.form.UserPostUpdateForm;
import grape.base.rest.userpost.mapper.UserPostWebMapper;
import grape.base.rest.userpost.vo.UserPostVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.po.UserPost;
import grape.common.exception.ExceptionTools;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/userpost")
@Api(tags = "用户岗位信息相关接口")
public class UserPostController extends BaseLoginUserController<UserPostVo, UserPost, LoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeUserPost");

    @Autowired
    private UserPostWebMapper currentWebMapper;
    @Autowired
    private IUserPostService currentService;
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

     @ApiOperation("添加用户岗位")
     @PreAuthorize("hasAuthority('userPost:single:create')")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo create(@RequestBody @Valid UserPostCreateForm cf) {
         UserPost po = currentWebMapper.formToPo(cf);
         // 主岗已存在，不能再添加主岗
         if (po.getIsMain()) {
             UserPost userPost = currentService.getMainByUserId(cf.getUserId());
             if(userPost != null){
                 throw ExceptionTools.failRE("已存在主岗");
             }
         }
         return super.create(po);
     }

     @ApiOperation("根据id查询用户岗位")
     @PreAuthorize("hasAuthority('userPost:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("根据id删除用户岗位")
     @PreAuthorize("hasAuthority('userPost:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("根据id更新用户岗位")
     @PreAuthorize("hasAuthority('userPost:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public UserPostVo update(@PathVariable String id,@RequestBody @Valid UserPostUpdateForm uf) {
         UserPost po = currentWebMapper.formToPo(uf);
         po.setId(id);
         // 主岗已存在，不能再添加主岗
         if (po.getIsMain()) {
             UserPost userPost = currentService.getMainByUserId(uf.getUserId());
             if(userPost != null && !isEqual(id,userPost.getId())){
                 throw ExceptionTools.failRE("已存在主岗");
             }
         }
         return super.update(po);
     }

    @ApiOperation("分页查询用户岗位信息")
    @PreAuthorize("hasAuthority('userPost:single:listPage')")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<UserPostVo> listPage(@Valid UserPostListPageForm listPageForm) {
         UserPost po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    /**
     * 启用或禁用
     * @param id
     * @param form
     * @return
     */
    @ApiOperation("启用或禁用")
    @PreAuthorize("hasAuthority('userPost:single:effect')")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPostVo enable(@PathVariable String id, @RequestBody @Valid UserPostEffectForm form) {
        UserPost role = new UserPost();
        role.setId(id);
        role.setIsEffect(form.getIsEffect());
        role.setVersion(form.getVersion());
        role.setIneffectReason(form.getIneffectReason());
        return super.update(role);
    }
}
