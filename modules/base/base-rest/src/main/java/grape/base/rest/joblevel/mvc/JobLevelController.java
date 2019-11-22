package grape.base.rest.joblevel.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.joblevel.form.JobLevelCreateForm;
import grape.base.rest.joblevel.form.JobLevelListForm;
import grape.base.rest.joblevel.form.JobLevelListPageForm;
import grape.base.rest.joblevel.form.JobLevelUpdateForm;
import grape.base.rest.joblevel.mapper.JobLevelWebMapper;
import grape.base.rest.joblevel.vo.JobLevelVo;
import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.joblevel.po.JobLevel;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 职务级别表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/joblevel")
@Api(tags = "职务级别相关接口")
public class JobLevelController extends BaseController<JobLevelVo, JobLevel> {

    @Autowired
    private JobLevelWebMapper currentWebMapper;
    @Autowired
    private IJobLevelService currentService;

    @Autowired
    private IJobService iJobService;


     @ApiOperation("添加职务级别")
     @RequiresPermissions("jobLevel:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public JobLevelVo create(@RequestBody @Valid JobLevelCreateForm cf) {

         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         JobLevel po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询职务级别")
     @RequiresPermissions("jobLevel:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public JobLevelVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除职务级别")
     @RequiresPermissions("jobLevel:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新职务级别")
     @RequiresPermissions("jobLevel:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public JobLevelVo update(@PathVariable String id,@RequestBody @Valid JobLevelUpdateForm uf) {
         JobLevel po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询职务级别")
    @RequiresPermissions("jobLevel:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<JobLevelVo> listPage(JobLevelListPageForm listPageForm) {
         JobLevel po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

    /**
     * 列出职务
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询职务级别",notes = "可用于下拉搜索")
    @RequiresPermissions("jobLevel:single:list")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<JobLevelVo> list(JobLevelListForm listForm) {
        JobLevel po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }

    @Override
    public JobLevelVo transVo(JobLevelVo jobLevelVo) {
        Job job = iJobService.getById(jobLevelVo.getJobId());
        String jobName = Optional.ofNullable(job).map(Job::getName).orElse(null);
        jobLevelVo.setJobName(jobName);
        return jobLevelVo;
    }
}
