package grape.common.rest.mvc;

import grape.common.exception.ExceptionTools;
import grape.common.rest.form.BaseForm;
import grape.common.rest.vo.BaseVo;
import grape.common.service.IBaseService;
import grape.common.service.po.NormalBasePo;
import grape.common.tools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yangwei
 * Created at 2019/7/22 18:26
 */
public abstract class SuperController<Service extends IBaseService<Po>,Vo extends BaseVo,Po extends NormalBasePo,CreateForm extends BaseForm> extends ToolService {

    @Autowired
    private Service service;
    /**
     * 单表添加
     * @param cf
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vo create(CreateForm cf){
        Po poQuery = this.createFormToPo(cf);
        Po dbPo = service.create(poQuery);
        if (dbPo == null) {
            throw ExceptionTools.newRE("添加失败");
        }
        Vo vo = this.poToVo(dbPo);
        return vo;
    }

    /**
     * 单表根据id获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vo queryById(@PathVariable String id){
        Po dbPo = service.getById(id);
        Vo vo = this.poToVo(dbPo);
        if (vo == null) {
            throw ExceptionTools.dataNotExistRE(null);
        }
        return vo;
    }
    public abstract Po createFormToPo(CreateForm cf);
    public abstract Vo poToVo(Po po);
}
