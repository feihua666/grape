package grape.common.rest.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.common.exception.ExceptionTools;
import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.vo.BaseVo;
import grape.common.service.IBaseService;
import grape.common.service.po.NormalBasePo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 正常业务实体的controller基类,提供一些通用方法
 * @param <Service> service
 * @param <Vo> 简单vo
 * @param <Po> po
 * @param <CreateForm> 添加数据表单
 * @param <UpdateForm> 更新表单
 * @param <ListForm> 列表查询表单
 */
public abstract class BaseController<Service extends IBaseService<Po>,Vo extends BaseVo,Po extends NormalBasePo<Po>,CreateForm extends BaseForm, UpdateForm extends BaseForm, ListForm extends BasePageForm> extends SuperController {

    @Autowired
    private Service service;
    /**
     * 单表添加
     * @param cf
     * @return
     */
    @PostMapping
    @ApiOperation("")
    @ResponseStatus(HttpStatus.CREATED)
    public Vo create(@RequestBody @Valid CreateForm cf){
        Po poQuery = this.createFormToPo(cf);
        Po dbPo = service.create(poQuery);
        if (dbPo == null) {
            throw ExceptionTools.newRE("添加失败");
        }
        Vo vo = this.poToVo(dbPo);
        return queryById(dbPo.getId());
    }

    /**
     * 单表根据id获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vo queryById(@PathVariable Long id){
        Po dbPo = service.getById(id);
        Vo vo = this.poToVo(dbPo);
        if (vo == null) {
            throw ExceptionTools.dataNotExistRE(null);
        }
        return vo;
    }
    /**
     * 单表根据id获取
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteById(@PathVariable Long id){
        boolean r = service.removeById(id);
        if (!r) {
            // 如果删除失败，查询数据库中是否存在
            if (service.getById(id) == null) {
                throw ExceptionTools.dataNotExistRE("数据不存在，请刷新后再试");
            }
            throw ExceptionTools.failRE("删除失败");
        }
        // 返回一个空数据，如果这里返回null，并不会被GlobalResponseBodyAdvice 统一包装，所以返回一个空对象，并不是null
        return new Object();
    }

    /**
     * 单表更新
     * @param uf
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vo update(@PathVariable Long id,@RequestBody UpdateForm uf){

        Po poQuery = updateFormToPo(uf);
        boolean r = service.updateById(poQuery);
        if (!r) {
            throw ExceptionTools.failRE("更新失败，请刷新数据后再试");
        }
        return poToVo(service.getById(id));
    }

    @GetMapping("/listPage")
    @ResponseStatus(HttpStatus.OK)
    public IPage<Vo> listPage(ListForm listForm){
        Po poQuery = this.listPageFormToPo(listForm);
        IPage<Po> page = service.page(new Page(listForm.getCurrent(),listForm.getSize()),new QueryWrapper(poQuery));
        return pagePoToVo(page);

    }
    /**
     * 添加数据表单转po
     * 仅适用于简单的单表对象
     * 不建议用复制属性工具类，直接set方法更直观，性能好
     * @param cf
     * @return
     */
    public abstract Po createFormToPo(CreateForm cf);
    public abstract Po updateFormToPo(UpdateForm uf);
    public abstract Po listPageFormToPo(ListForm lf);

    public IPage<Vo> pagePoToVo(IPage page){
        List<Po> records = page.getRecords();
        if (page != null && !isListEmpty(records)) {
            List<Vo> voList = Arrays.asList();
            voList = records.stream().map(po ->  poToVo(po)).collect(Collectors.toList());
            page.setRecords(voList);
            return page;
        }
        return null;
    }
    /**
     * po转vo
     * 仅适用于简单的单表对象
     * 不建议用复制属性工具类，直接set方法更直观，性能好
     * @param po
     * @return 返回响应数据
     */
    public abstract Vo poToVo(Po po);
}
