package grape.common.rest.mvc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.common.AbstractLoginUser;
import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.IBaseService;
import grape.common.service.IBaseTreeService;
import grape.common.service.po.IDBasePo;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public Vo create(Po poQuery){
        Po dbPo = null;
        if(service instanceof IBaseTreeService){
            String parentId = ((TreeBasePo) poQuery).getParentId();
            if (!isStrEmpty(parentId)) {
                dbPo = (Po) ((IBaseTreeService) service).createChild((TreeBasePo) poQuery,parentId);
            }
            else {
                dbPo = service.create(poQuery);
            }
        }else {
            dbPo = service.create(poQuery);
        }
        if (dbPo == null) {
            throw ExceptionTools.newRE("添加失败");
        }
        Vo vo = mapperConverter.poToVo(dbPo);
        vo = transVo(vo);
        return vo;
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
     * 单表根据id获取
     * @param id
     * @return
     */

    public boolean deleteById(String id){
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

    public Vo update(Po poQuery){
        if(service instanceof IBaseTreeService){
            // 判断父级是否修改
            Po poDb = getService().getById(poQuery.getId().toString());
            //父级不相等，则有修改父级
            if(!isEqual(((TreeBasePo) poDb).getParentId(), ((TreeBasePo) poQuery).getParentId())){
                // 判断该节点下是否有子节点，如果有，不允许修改
                int childrenCount = ((IBaseTreeService) service).getChildrenCount(poQuery.getId().toString());
                if (childrenCount > 0) {
                    throw new InvalidParamsException("当前节点下还有子节点，不允许修改父节点");
                }
            }

            if (!isStrEmpty(((TreeBasePo) poQuery).getParentId())) {
                poQuery = (Po) ((IBaseTreeService) service).initParentIdXByParent((TreeBasePo) poQuery, ((TreeBasePo) poQuery).getParentId());
            }
        }
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
        IPage<Po> page = service.page(new Page(pageForm.getCurrent(),pageForm.getSize()),new QueryWrapper(poQuery));
        if (page.getTotal() == 0) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        return pagePoToVo(page);

    }
    public List<Vo> list(Po poQuery){
        List list = service.list(new QueryWrapper(poQuery));
        return posToVos(list);
    }
    /**
     * 根据父id请求
     * @param parentId
     * @return
     */
    public List<Vo> getByParentId(String parentId){
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

    /**
     * 检查机构树结构是否完整
     */

    public <Po extends TreeBasePo<Po>> boolean checkTreeStruct(){
        if(service instanceof IBaseTreeService){
            try {
                ((IBaseTreeService<Po>) service).checkTreeStruct(null);
            } catch (CBaseException e) {
                throw new RBaseException(e.getMessage());
            }
        }else {
            throw new RBaseException("当前不支持该操作");
        }
        return true;
    }

    /**
     * pos转vos
     * @param pos
     * @return
     */
    public List<Vo> posToVos(List<Po> pos) {
        List<Vo> vos = new ArrayList<>(pos.size());
        for (Po po : pos) {
            vos.add(transVo(mapperConverter.poToVo(po)));
        }
        return vos;
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
     * 只是简单的对象转换，支持懒加载
     * @param list
     * @param <T>
     * @return
     */
    public <T extends BaseTreeVo> List<TreeNodeVo<T>> listToTreeNodeVo(List<T> list){
        List<TreeNodeVo<T>> temp = new ArrayList<>(list.size());
        TreeNodeVo tempTreeNodeVo = null;
        for (T t : list) {
            tempTreeNodeVo = new TreeNodeVo<>(t,null,false);
            tempTreeNodeVo.setId(t.getId());
            tempTreeNodeVo.setHasChildren(((IBaseTreeService) service).hasChildren(t.getId()));
            temp.add(tempTreeNodeVo);
        }
        return temp;
    }
    /**
     * list转为树结构
     * 主要是将打平的树数据转为树结构，不支持懒加载使用
     * @param list
     * @param <T>
     * @return
     */
    public <T extends BaseTreeVo> List<TreeNodeVo<T>> listToTree(List<T> list){
        List<TreeNodeVo<T>> temp = listToTreeNodeVo(list);
        List<TreeNodeVo<T>> result = new ArrayList<>();
        TreeNodeVo tempTreeNodeVo = null;
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
                    tempTreeNodeVo = new TreeNodeVo<>(treeNodeVo.getNode(),null,false);
                    tempTreeNodeVo.setId(treeNodeVo.getNode().getId());
                    next.setHasChildren(true);
                    next.getChildren().add(tempTreeNodeVo);
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
