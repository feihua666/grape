package grape.base.rest.workcalendar.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.validation.Valid;
import grape.base.rest.workcalendar.form.WorkCalendarCreateForm;
import grape.base.rest.workcalendar.form.WorkCalendarUpdateForm;
import grape.base.rest.workcalendar.form.WorkCalendarListPageForm;
import grape.base.rest.workcalendar.vo.WorkCalendarVo;
import grape.base.rest.workcalendar.mapper.WorkCalendarWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import grape.common.rest.mvc.BaseController;
import grape.base.service.workcalendar.po.WorkCalendar;
import grape.base.service.workcalendar.api.IWorkCalendarService;
import java.util.List;
/**
 * <p>
 * 工作日历 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/workcalendar")
@Api(tags = "工作日历相关接口")
public class WorkCalendarController extends BaseController<WorkCalendarVo, WorkCalendar> {

    @Autowired
    private WorkCalendarWebMapper currentWebMapper;
    @Autowired
    private IWorkCalendarService currentService;



     @ApiOperation("添加工作日历")
     @RequiresPermissions("workcalendar:single:create")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public WorkCalendarVo create(@RequestBody @Valid WorkCalendarCreateForm cf) {
         WorkCalendar po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询工作日历")
     @RequiresPermissions("workcalendar:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public WorkCalendarVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除工作日历")
     @RequiresPermissions("workcalendar:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

     @ApiOperation("更新工作日历")
     @RequiresPermissions("workcalendar:single:updateById")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public WorkCalendarVo update(@PathVariable String id,@RequestBody @Valid WorkCalendarUpdateForm uf) {
         WorkCalendar po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询工作日历")
    @RequiresPermissions("workcalendar:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<WorkCalendarVo> listPage(WorkCalendarListPageForm listPageForm) {
         WorkCalendar po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
