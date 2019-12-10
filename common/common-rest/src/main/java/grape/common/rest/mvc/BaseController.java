package grape.common.rest.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.common.AbstractLoginUser;
import grape.common.exception.ExceptionTools;
import grape.common.rest.form.BasePageForm;
import grape.common.service.common.IBaseService;
import grape.common.service.po.IDBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 正常业务实体的controller基类,提供一些通用方法
 * @param <Vo> 简单vo
 * @param <Po> po
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BaseController<Vo,Po extends IDBasePo<?,?>> extends SuperController {

    @Autowired
    private IBaseService<Po> service;

    @Autowired
    private WebMapper<Vo,Po> mapperConverter;


    /**
     * 单表添加
     * @param cf
     * @return
     */
    public Vo create(Po poQuery){
        Po dbPo = service.create(poQuery);
        return returnCreate(dbPo);
    }

    /**
     * 单表根据id获取
     * @param id
     * @return
     */

    public Vo queryById(String id){
        Po dbPo = service.getById(id);
        Vo vo = mapperConverter.poToVo(dbPo);
        if (vo == null) {
            throw ExceptionTools.dataNotExistRE(null);
        }
        vo = transVo(vo);
        return vo;
    }
    /**
     * 单表根据id删除
     * @param id
     * @return
     */

    public boolean deleteById(String id){

        boolean r = service.removeById(id);
        if (!r) {
            throw ExceptionTools.dataNotExistRE("数据不存在，请刷新后再试");
        }
        // 如果这里返回null，并不会被GlobalResponseBodyAdvice 统一包装
        return r;
    }
    /**
     * 单表更新
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery){
        boolean r = service.updateById(poQuery);
        if (!r) {
            throw ExceptionTools.failRE("更新失败，请刷新数据后再试");
        }
        Vo vo = mapperConverter.poToVo(service.getById(poQuery.getId().toString()));
        vo = transVo(vo);
        return vo;
    }
    /**
     * 分页查询
     * @param poQuery
     * @param pageForm
     * @return
     */
    public IPage<Vo> listPage(Po poQuery, BasePageForm pageForm){
        IPage<Po> page = service.page(new Page(pageForm.getCurrent(),pageForm.getSize()),poQuery);
        if (page.getTotal() == 0) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        return pagePoToVo(page);

    }

    /**
     * 不分页查询
     * @param poQuery
     * @return
     */
    public List<Vo> list(Po poQuery){
        List list = service.list(poQuery);
        return posToVos(list);
    }

    /**
     * 用来返回添加后的响应
     * @param dbPo
     * @return
     */
    protected Vo returnCreate(Po dbPo){
        if (dbPo == null) {
            throw ExceptionTools.newRE("添加失败");
        }
        Vo vo = mapperConverter.poToVo(dbPo);
        vo = transVo(vo);
        return vo;
    }

    /**
     * pos转vos
     * @param pos
     * @return
     */
    public List<Vo> posToVos(List<Po> pos) {
        return pos.stream().map(po->transVo(mapperConverter.poToVo(po))).collect(Collectors.toList());
    }

    /**
     * 分页po转vo
     * @param page
     * @return
     */
    public IPage<Vo> pagePoToVo(IPage page){
        List<Po> records = page.getRecords();
        if (page != null && !isListEmpty(records)) {
            page.setRecords(posToVos(records));
            return page;
        }
        // 原样返回page
        return page;
    }
    /**
     * 翻译vo的额外属性，如：字典id换名称，但一般提供了翻译注解，除非特别复杂的翻译直接手动写，否则直接实现ITransService接口即可
     * @param vo
     * @return
     */
    public Vo transVo(Vo vo){
        return vo;
    }
}
