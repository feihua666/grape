package grape.common.rest.mvc;

import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

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
public abstract class BaseTreeController<Vo extends BaseTreeVo,Po extends TreeBasePo<Po>> extends BaseController<Vo,Po> {

    @Autowired
    private IBaseTreeService<Po> service;

    @Autowired
    private WebMapper<Vo,Po> mapperConverter;

    /**
     * 单表添加
     * @param poQuery
     * @return
     */
    public Vo create(Po poQuery){
        Po dbPo = null;
        String parentId = poQuery.getParentId();
        if (!isStrEmpty(parentId)) {
            dbPo = service.createChild(poQuery,parentId);
            return returnCreate(dbPo);
        }else {
            return super.create(poQuery);
        }
    }

    /**
     * 单表根据id获取
     * @param id
     * @return
     */

    public boolean deleteById(String id){
        if (service.getChildrenCount(id) > 0) {
            throw ExceptionTools.failRE("删除失败,当前节点下还有子节点");
        }
        return super.deleteById(id);
    }
    /**
     * 单表更新
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery){
        poQuery = beforUpdate(poQuery);
        return super.update(poQuery);
    }
    protected Po beforUpdate(Po poQuery){
        // 判断父级是否修改
        Po poDb = getService().getById(poQuery.getId());
        //父级不相等，则有修改父级
        if(!isEqual(poDb.getParentId(), poQuery.getParentId())){
            // 判断该节点下是否有子节点，如果有，不允许修改
            int childrenCount = service.getChildrenCount(poQuery.getId());
            if (childrenCount > 0) {
                throw new InvalidParamsException("当前节点下还有子节点，不允许修改父节点");
            }
        }
        if (!isStrEmpty(poQuery.getParentId())) {
            poQuery = service.initParentIdXByParent(poQuery, poQuery.getParentId());
        }
        return poQuery;
    }

    /**
     * 根据父id请求
     * @param parentId
     * @return
     */
    public List<Vo> getByParentId(String parentId){
        List<Po> r;
        if (parentId == null) {
            r = ((IBaseTreeService) service).getRoot();
        }else {
            r = ((IBaseTreeService) service).getChildren(parentId);
        }
        return super.posToVos(r);
    }

    /**
     * 检查机构树结构是否完整
     */

    public boolean checkTreeStruct(){
        try {
            service.checkTreeStruct(null);
        } catch (CBaseException e) {
            throw new RBaseException(e.getMessage());
        }
        return true;
    }


    /**
     * 只是简单的对象转换，支持懒加载
     * @param list
     * @return
     */
    public  List<TreeNodeVo<Vo>> listToTreeNodeVo(List<Vo> list){
        List<TreeNodeVo<Vo>> temp = new ArrayList<>(list.size());
        TreeNodeVo tempTreeNodeVo = null;
        for (Vo t : list) {
            tempTreeNodeVo = new TreeNodeVo<>(t,null,false);
            tempTreeNodeVo.setId(t.getId());
            tempTreeNodeVo.setHasChildren(service.hasChildren(t.getId()));
            temp.add(tempTreeNodeVo);
        }
        return temp;
    }
    /**
     * list转为树结构
     * 主要是将打平的树数据转为树结构，不支持懒加载使用
     * @param list
     * @return
     */
    public List<TreeNodeVo<Vo>> listToTree(List<Vo> list){
        List<TreeNodeVo<Vo>> temp = listToTreeNodeVo(list);
        List<TreeNodeVo<Vo>> result = new ArrayList<>();
        TreeNodeVo tempTreeNodeVo = null;
        Iterator<TreeNodeVo<Vo>> iterator = temp.iterator();
        while (iterator.hasNext()) {
            TreeNodeVo<Vo> next = iterator.next();
            if (next.getNode().getParentId() == null) {
                result.add(next);
                iterator.remove();
            }
            for (TreeNodeVo<Vo> treeNodeVo : temp) {
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
}
