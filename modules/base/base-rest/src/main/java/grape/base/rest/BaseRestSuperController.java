package grape.base.rest;

import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.mvc.BaseController;
import grape.common.rest.mvc.WebMapper;
import grape.common.rest.vo.BaseVo;
import grape.common.service.IBaseService;
import grape.common.service.po.IDBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * base rest 项目通过controller通用基类，主要提供一些公共service注入
 */

public class BaseRestSuperController<Service extends IBaseService<Po>,MapperConverter extends WebMapper<Vo,Po,CreateForm,UpdateForm,ListForm>,Vo extends BaseVo,Po extends IDBasePo<Long,Po>,CreateForm extends BaseForm, UpdateForm extends BaseForm, ListForm extends BasePageForm>
        extends BaseController<Service,MapperConverter, Vo, Po, CreateForm,UpdateForm,ListForm> {
    @Getter
    @Autowired
    private IDictService iDictService;
    @Getter
    @Autowired(required = false)
    private IDeptService iDeptService;
    @Getter
    @Autowired(required = false)
    private ICompService iCompService;

    /**
     * 获取公司
     * @param id
     * @return
     */
    public Comp getCompById(Serializable id){
        if (iCompService != null) {
            return iCompService.getById(id);
        }
        return null;
    }

    /**
     * 获取字典
     * @param id
     * @return
     */
    public Dict getDictById(Serializable id){
        if (iDictService != null) {
            return iDictService.getById(id);
        }
        return null;
    }

    /**
     * 获取部门
     * @param id
     * @return
     */
    public Dept getDeptById(Serializable id){
        if (iDeptService != null) {
            return iDeptService.getById(id);
        }
        return null;
    }
}
