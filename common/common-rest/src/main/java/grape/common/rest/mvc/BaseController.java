package grape.common.rest.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.common.AbstractLoginUser;
import grape.common.exception.ExceptionTools;
import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.BaseVo;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.IBaseService;
import grape.common.service.IBaseTreeService;
import grape.common.service.po.IDBasePo;
import grape.common.service.po.NormalBasePo;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
@Data
public abstract class BaseController<Service extends IBaseService<Po>,MapperConverter extends WebMapper<Vo,Po,CreateForm,UpdateForm,ListForm>,Vo extends BaseVo,Po extends IDBasePo<Long,Po>,CreateForm extends BaseForm, UpdateForm extends BaseForm, ListForm extends BasePageForm> extends SuperController {

    @Getter
    @Autowired
    private Service service;
    @Getter
    @Autowired
    private MapperConverter mapperConverter;


    /**
     * 获取当前登录用户
     * @return
     */
    protected AbstractLoginUser getLoginUser(){
        return AbstractLoginUser.getLoginUser();
    }
    /**
     * 单表添加
     * @param cf
     * @return
     */
    public Vo create(CreateForm cf){
        Po poQuery =  mapperConverter.createFormToPo(cf);
        Po dbPo = service.create(poQuery);
        if (dbPo == null) {
            throw ExceptionTools.newRE("添加失败");
        }
        Vo vo = mapperConverter.poToVo(dbPo);
        return vo;
    }

    /**
     * 单表根据id获取
     * @param id
     * @return
     */

    public Vo queryById(Long id){
        Po dbPo = service.getById(id);
        Vo vo = mapperConverter.poToVo(dbPo);
        if (vo == null) {
            throw ExceptionTools.dataNotExistRE(null);
        }
        vo = transVo(vo);
        return vo;
    }
    /**
     * 单表根据id获取
     * @param id
     * @return
     */

    public boolean deleteById(Long id){
        if(service instanceof IBaseTreeService){
            if (((IBaseTreeService) service).getChildrenCount(id) > 0) {
                throw ExceptionTools.failRE("删除失败,当前节点下还有子节点");
            }
        }
        boolean r = service.removeById(id);
        if (!r) {
            // 如果删除失败，查询数据库中是否存在
            if (service.getById(id) == null) {
                throw ExceptionTools.dataNotExistRE("数据不存在，请刷新后再试");
            }
            throw ExceptionTools.failRE("删除失败，未知原因");
        }
        // 如果这里返回null，并不会被GlobalResponseBodyAdvice 统一包装
        return r;
    }

    /**
     * 单表更新
     * @param uf
     * @return
     */

    public Vo update(@PathVariable Long id,@RequestBody UpdateForm uf){

        Po poQuery = mapperConverter.updateFormToPo(uf);
        boolean r = service.updateById(poQuery);
        if (!r) {
            throw ExceptionTools.failRE("更新失败，请刷新数据后再试");
        }
        Vo vo = mapperConverter.poToVo(service.getById(id));
        vo = transVo(vo);
        return vo;
    }

    public IPage<Vo> listPage(ListForm listForm){
        Po poQuery = mapperConverter.listPageFormToPo(listForm);
        IPage<Po> page = service.page(new Page(listForm.getCurrent(),listForm.getSize()),new QueryWrapper(poQuery));
        if (page.getTotal() == 0) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        return pagePoToVo(page);

    }

    /**
     * 树请求
     * @param parentId
     * @return
     */
    public List<Vo> tree(Long parentId){
        if(service instanceof IBaseTreeService){
            List<Po> r = new ArrayList<>();
            if (parentId == null) {
                r = ((IBaseTreeService) service).getRoot();
            }else {
                r = ((IBaseTreeService) service).getChildren(parentId);
            }
            List<Vo> rv = new ArrayList<>(r.size());
            for (Po po : r) {

                rv.add(transVo(mapperConverter.poToVo(po)));
            }
            return rv;
        }else {
            throw ExceptionTools.failRE("当前操作不支持,没有可用的service");
        }
    }

    public List<Vo> posToVos(List<Po> pos) {
        List<Vo> vos = new ArrayList<>(pos.size());
        for (Po po : pos) {
            vos.add(transVo(mapperConverter.poToVo(po)));
        }
        return vos;
    }

    public IPage<Vo> pagePoToVo(IPage page){
        List<Po> records = page.getRecords();
        if (page != null && !isListEmpty(records)) {
            List<Vo> voList = Arrays.asList();
            voList = records.stream().map(po ->
                    transVo(mapperConverter.poToVo(po))
            ).collect(Collectors.toList());
            page.setRecords(voList);
            return page;
        }
        // 原样返回page
        return page;
    }

    /**
     * list转为树结构
     * @param list
     * @param <T>
     * @return
     */
    public <T extends BaseTreeVo> List<TreeNodeVo<T>> listToTree(List<T> list){
        List<TreeNodeVo<T>> temp = new ArrayList<>();
        List<TreeNodeVo<T>> result = new ArrayList<>();
        for (T t : list) {
            temp.add(new TreeNodeVo<>(t,null));
        }
        Iterator<TreeNodeVo<T>> iterator = temp.iterator();
        while (iterator.hasNext()) {
            TreeNodeVo<T> next = iterator.next();
            if (next.getNode().getParentId() == null) {
                result.add(next);
                iterator.remove();
            }
            for (TreeNodeVo<T> treeNodeVo : temp) {
                if (next.getNode().getId().equals(treeNodeVo.getNode().getParentId())) {
                    if (next.getChildren() == null) {
                        next.setChildren(new ArrayList<>());
                    }
                    next.getChildren().add(new TreeNodeVo<>(treeNodeVo.getNode(),null));
                }
            }
        }

        return result;
    }

    /**
     * 翻译vo的是外键属性，如：字典id换名称
     * @param vo
     * @return
     */
    public Vo transVo(Vo vo){
        return vo;
    }
}
