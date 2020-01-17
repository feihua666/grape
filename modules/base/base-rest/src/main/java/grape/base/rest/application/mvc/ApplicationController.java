package grape.base.rest.application.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.base.rest.application.form.ApplicationCreateForm;
import grape.base.rest.application.form.ApplicationListForm;
import grape.base.rest.application.form.ApplicationListPageForm;
import grape.base.rest.application.form.ApplicationUpdateForm;
import grape.base.rest.application.mapper.ApplicationWebMapper;
import grape.base.rest.application.vo.ApplicationVo;
import grape.common.exception.runtime.RDataNotExistException;
import grape.common.rest.security.UserDetailsClient;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.application.api.IApplicationService;
import grape.base.service.application.po.Application;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
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
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 应用表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/application")
@Api(tags = "应用相关接口")
public class ApplicationController extends BaseLoginUserController<ApplicationVo, Application, LoginUser> {

    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeApplication");

    @Autowired
    private ApplicationWebMapper currentWebMapper;
    @Autowired
    private IApplicationService currentService;

    @Autowired
    private IFuncService iFuncService;

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

     @ApiOperation("添加应用")
     @PreAuthorize("hasAuthority('application:single:create')")
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     public ApplicationVo create(@RequestBody @Valid ApplicationCreateForm cf) {
         // code 唯一检查
         if (currentService.existCode(cf.getCode())) {
             throw new RBaseException("编码已存在");
         }
         Application po = currentWebMapper.formToPo(cf);
         return super.create(po);
     }

     @ApiOperation("根据ID查询应用")
     @PreAuthorize("hasAuthority('application:single:queryById')")
     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ApplicationVo queryById(@PathVariable String id) {
         return super.queryById(id);
     }

     @ApiOperation("删除应用")
     @PreAuthorize("hasAuthority('application:single:deleteById')")
     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public boolean deleteById(@PathVariable String id) {
         // 不能删除已使用的应用

         if (iFuncService.count(new Func().setApplicationId(id)) > 0) {
             throw new RBaseException("有功能在使用不能删除");
         }
         return super.deleteById(id);
     }

     @ApiOperation("更新应用")
     @PreAuthorize("hasAuthority('application:single:updateById')")
     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.CREATED)
     public ApplicationVo update(@PathVariable String id,@RequestBody @Valid ApplicationUpdateForm uf) {
         Application po = currentWebMapper.formToPo(uf);
         po.setId(id);
         return super.update(po);
     }

    @ApiOperation("分页查询应用")
    @PreAuthorize("hasAuthority('application:single:listPage')")
    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<ApplicationVo> listPage(ApplicationListPageForm listPageForm) {
         Application po = currentWebMapper.formToPo(listPageForm);
         return super.listPage(po,listPageForm);
     }
    /**
     * 列出应用
     * @param listForm
     * @return
     */
    @ApiOperation(value = "不分页查询应用",notes = "可用于下拉或下拉搜索")
    @PreAuthorize("hasAuthority('application:single:list')")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationVo> list(ApplicationListForm listForm) {
        Application po = currentWebMapper.formToPo(listForm);
        return super.list(po);
    }


    @Autowired
    private UserDetailsClient userDetailsClient;
    /**
     * 当前登录用户的应用
     * @return
     */
    @ApiOperation(value = "当前登录用户的应用",notes = "可用于下拉或下拉搜索")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/list/current/user")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationVo> list() {
        LoginUser loginUser = getLoginUser();
        if (loginUser.superAdmin()) {
            return super.posToVos((List<Application>) currentService.list());
        }
        List<String> applicationIds = userDetailsClient.getApplicationIdsByUserId(getLoginUserId());
        if (isEmpty(applicationIds)) {
            throw new RDataNotExistException("数据不存在");
        }
        return super.posToVos((List<Application>) currentService.listByIds(applicationIds));
    }
}
