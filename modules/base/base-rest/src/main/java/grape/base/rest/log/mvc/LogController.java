package grape.base.rest.log.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.log.form.LogListPageForm;
import grape.base.rest.log.mapper.LogWebMapper;
import grape.base.rest.log.vo.LogVo;
import grape.base.service.log.api.ILogService;
import grape.base.service.log.po.Log;
import grape.common.rest.mvc.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/log")
@Api(tags = "系统日志相关接口")
public class LogController extends BaseController<LogVo, Log> {

    @Autowired
    private LogWebMapper currentWebMapper;
    @Autowired
    private ILogService currentService;



     @ApiOperation("根据ID查询系统日志")
     @RequiresPermissions("log:single:queryById")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public LogVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除系统日志")
     @RequiresPermissions("log:single:deleteById")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         return super.deleteById(id);
     }

    @ApiOperation("分页查询系统日志")
    @RequiresPermissions("log:single:listPage")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<LogVo> listPage(LogListPageForm listPageForm) {
         Log po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }

}
