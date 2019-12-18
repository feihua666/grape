package grape.base.rest.userpost.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.userpost.form.UserPostCreateForm;
import grape.base.rest.userpost.form.UserPostEffectForm;
import grape.base.rest.userpost.form.UserPostListPageForm;
import grape.base.rest.userpost.form.UserPostUpdateForm;
import grape.base.rest.userpost.mapper.UserPostWebMapper;
import grape.base.rest.userpost.vo.UserPostVo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.joblevel.po.JobLevel;
import grape.base.service.post.api.IPostService;
import grape.base.service.post.po.Post;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.po.UserPost;
import grape.common.exception.ExceptionTools;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class UserPostController extends BaseController<UserPostVo, UserPost> {

    @Autowired
    private UserPostWebMapper currentWebMapper;
    @Autowired
    private IUserPostService currentService;


     @ApiOperation("添加用户岗位")
     @RequiresPermissions("userPost:single:create")
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
     @RequiresPermissions("userPost:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public UserPostVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("根据id删除用户岗位")
     @RequiresPermissions("userPost:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("根据id更新用户岗位")
     @RequiresPermissions("userPost:single:updateById")
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
    @RequiresPermissions("userPost:single:listPage")
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
    @RequiresPermissions("userPost:single:effect")
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
