package grape.base.rest.joblevel.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.joblevel.form.JobLevelCreateForm;
import grape.base.rest.joblevel.form.JobLevelListForm;
import grape.base.rest.joblevel.form.JobLevelListPageForm;
import grape.base.rest.joblevel.form.JobLevelUpdateForm;
import grape.base.rest.joblevel.mapper.JobLevelWebMapper;
import grape.base.rest.joblevel.vo.JobLevelVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.job.api.IJobService;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.joblevel.po.JobLevel;
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
 * 职务级别表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/joblevel")
@Api(tags = "职务级别相关接口")
public class JobLevelController extends BaseLoginUserController<JobLevelVo, JobLevel, LoginUser> {
    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeJobLevel");

    @Autowired
    private JobLevelWebMapper currentWebMapper;
    @Autowired
    private IJobLevelService currentService;

    @Autowired
    private IJobService iJobService;


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



    @ApiOperation("添加职务级别")
     @PreAuthorize("hasAuthority('jobLevel:single:create')")
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
     @PreAuthorize("hasAuthority('jobLevel:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public JobLevelVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除职务级别")
     @PreAuthorize("hasAuthority('jobLevel:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新职务级别")
     @PreAuthorize("hasAuthority('jobLevel:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public JobLevelVo update(@PathVariable String id,@RequestBody @Valid JobLevelUpdateForm uf) {
         JobLevel po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询职务级别")
    @PreAuthorize("hasAuthority('jobLevel:single:listPage')")
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
    @PreAuthorize("hasAuthority('jobLevel:single:list')")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<JobLevelVo> list(JobLevelListForm listForm) {
        JobLevel po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }

}
