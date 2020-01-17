package grape.base.rest.job.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.job.form.JobCreateForm;
import grape.base.rest.job.form.JobListForm;
import grape.base.rest.job.form.JobListPageForm;
import grape.base.rest.job.form.JobUpdateForm;
import grape.base.rest.job.mapper.JobWebMapper;
import grape.base.rest.job.vo.JobVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.common.exception.runtime.RBaseException;
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
import java.util.List;
/**
 * <p>
 * 职务 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/job")
@Api(tags = "职务相关接口")
public class JobController extends BaseLoginUserController<JobVo, Job, LoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject( "dataObjectCodeJob");

    @Autowired
    private JobWebMapper currentWebMapper;
    @Autowired
    private IJobService currentService;
    @Autowired
    private IDictService iDictService;
    @Autowired
    private IDeptService iDeptService;

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


    @ApiOperation("添加职务")
     @PreAuthorize("hasAuthority('job:single:create')")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public JobVo create(@RequestBody @Valid JobCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         Job po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询职务")
     @PreAuthorize("hasAuthority('job:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public JobVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除职务")
     @PreAuthorize("hasAuthority('job:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新职务")
     @PreAuthorize("hasAuthority('job:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public JobVo update(@PathVariable String id,@RequestBody @Valid JobUpdateForm uf) {
         Job po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询职务")
    @PreAuthorize("hasAuthority('job:single:listPage')")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<JobVo> listPage(JobListPageForm listPageForm) {
         Job po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    /**
     * 列出职务
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询职务",notes = "可用于添加职级做下拉或下拉搜索")
    @PreAuthorize("hasAuthority('job:single:list')")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<JobVo> list(JobListForm listForm) {
        Job po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }
}
