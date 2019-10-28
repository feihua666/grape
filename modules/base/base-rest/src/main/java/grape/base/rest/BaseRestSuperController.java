package grape.base.rest;

import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import grape.common.rest.mvc.BaseController;
import grape.common.service.po.IDBasePo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * base rest 项目通过controller通用基类，主要提供一些公共service注入
 */

public class BaseRestSuperController<Vo,Po extends IDBasePo<?,?>>
        extends BaseController<Vo, Po> {
    @Getter
    @Autowired
    private IDictService iDictService;
    @Getter
    @Autowired(required = false)
    private IDeptService iDeptService;
    @Getter
    @Autowired(required = false)
    private ICompService iCompService;
    @Autowired
    private IUserService iUserService;

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

    /**
     * 获取部门
     * @param id
     * @return
     */
    public User getUserById(Serializable id){
        if (iUserService != null) {
            return iUserService.getById(id);
        }
        return null;
    }
}
