package grape.common.service.common;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.po.TreeBasePo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static grape.common.service.po.TreeBasePo.maxLevel;

/**
 * 树操作
 * Created by yangwei
 * Created at 2019/9/23 11:03
 */
public interface IBaseTreeService<Po extends TreeBasePo<Po>> extends IBaseService<Po> {


    default List<Po> getRoot(){
        Map<String,Object> p = new HashMap<>(2);
        p.put(TreeBasePo.COLUMN_LEVEL,1);
        p.put(TreeBasePo.COLUMN_PARENT_ID,null);
        return (List<Po>) listByMap(p);
    }
    /**
     * 查询子一级节点
     * @param parentId
     * @return
     */
    default List<Po> getChildren(String parentId){
        if (parentId == null) {
            return null;
        }
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeBasePo.COLUMN_PARENT_ID,parentId);
        return (List<Po>) listByMap(p);
    }
    /**
     * 查询子一级节点,是否有子节点
     * @param parentId
     * @return
     */
    default boolean hasChildren(String parentId){
        int count = getChildrenCount(parentId);
        return count > 0;
    }
    default List<Po> getAllChildren(String parentId){
        if (parentId == null) {
            return null;
        }
        Po parent = getById(parentId);
        if (parent == null) {
            return null;
        }
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeBasePo.COLUMN_PARENT_ID + parent.getLevel(),parentId);
        return (List<Po>) listByMap(p);
    }
    default int getChildrenCount(String parentId){
        if (parentId == null) {
            throw new InvalidParamsException("parentId不能为空");
        }
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeBasePo.COLUMN_PARENT_ID,parentId);
        return count(Wrappers.<Po>query().eq(TreeBasePo.COLUMN_PARENT_ID,parentId));
    }
    /**
     * 查询父级
     * @param id
     * @return
     */
    default Po getParent(String id){
        if (id == null) {
            return null;
        }
        Po po = getById(id);
        if (po != null) {
            return getById(po.getParentId());
        }
        return null;
    }

    /**
     * 获取所有父级
     * @param id
     * @return
     */
    default List<Po> getAllParents(String id){
        if (id == null) {
            return null;
        }
        Po dbPo = getParent(id);
        if (dbPo == null) {
            return null;
        }
        List<String> parentIds = new ArrayList<>(maxLevel - 1);
        if (dbPo.getParentId1() != null) {
            parentIds.add(dbPo.getParentId1());
        }
        if (dbPo.getParentId2() != null) {
            parentIds.add(dbPo.getParentId2());
        }
        if (dbPo.getParentId3() != null) {
            parentIds.add(dbPo.getParentId3());
        }
        if (dbPo.getParentId4() != null) {
            parentIds.add(dbPo.getParentId4());
        }
        if (dbPo.getParentId5() != null) {
            parentIds.add(dbPo.getParentId5());
        }
        if (dbPo.getParentId6() != null) {
            parentIds.add(dbPo.getParentId6());
        }
        if (dbPo.getParentId7() != null) {
            parentIds.add(dbPo.getParentId7());
        }
        if (dbPo.getParentId8() != null) {
            parentIds.add(dbPo.getParentId8());
        }
        if (dbPo.getParentId9() != null) {
            parentIds.add(dbPo.getParentId9());
        }
        if (dbPo.getParentId10() != null) {
            parentIds.add(dbPo.getParentId10());
        }
        return (List<Po>) listByIds(parentIds);
    }

    /**
     * 检查树结构是否完整
     * @throws CBaseException 返回不完整的异常
     * @param parent 对parent本身没有做检查，启用必须传null对全量做检查，如果不传null请确保parent本身的正确
     * @throws CBaseException
     */
    default void checkTreeStruct(Po parent) throws CBaseException {

        int level = parent == null? TreeBasePo.INIT_LEVEL:parent.getLevel() + 1;
        Map<String, String> parentIdx = new HashMap<>(10);
        for (int i = 1; i < maxLevel; i++) {
            String fieldName = TreeBasePo.PROPERTY_PARENT_ID + i;
            Object fieldValueObj = ReflectUtil.getFieldValue(parent, fieldName);
            String fieldValue = fieldValueObj == null ? null : fieldValueObj.toString();
            parentIdx.put(fieldName, parent == null ? null : fieldValue);
        }
        if (parent != null) {
            String fieldName = TreeBasePo.PROPERTY_PARENT_ID + parent.getLevel();
            parentIdx.put(fieldName, parent.getId());
        }
        List<Po> children = null;
        if (parent == null) {
            children = getRoot();
        }else {
            children = getChildren(parent.getId());
        }
        if (!isListEmpty(children)) {
            for (Po po : children) {
                if(po.getLevel() != level){
                    throw new CBaseException("id=" + po.getId() + " level应该为"+ level +"实际为" + po.getLevel());
                }
                for (int i = 1; i < maxLevel; i++) {
                    String fieldName = TreeBasePo.PROPERTY_PARENT_ID + i;
                    Object fieldValueObj = ReflectUtil.getFieldValue(po, fieldName);
                    String fieldValue = fieldValueObj == null ? null : fieldValueObj.toString();
                    if( !isEqual(parentIdx.get(fieldName),fieldValue)){
                        throw new CBaseException("id=" + po.getId() + " "+ (fieldName) +"应该为"+ parentIdx.get(fieldName) +"实际为" + fieldValue);
                    }
                }
                checkTreeStruct(po);
            }
        }

    }
    /**
     * 根据id和父id查询
     * @param id
     * @param parentId
     * @return
     */
    default Po getByIdAndParentId(String id,String parentId){
        if (isStrAnyEmpty(id,parentId)) {
            throw new InvalidParamsException("id 和 parentId" + "都不能为空");
        }
        return getOne(Wrappers.<Po>query().eq(TreeBasePo.COLUMN_PARENT_ID, parentId).eq(TreeBasePo.COLUMN_ID, id));
    }

    default Po initParentIdXByParent(Po child,String parentId){
        Po parent = getParent(parentId);
        return initParentIdXByParent(child, parent);
    }
    default Po initParentIdXByParent(Po child,Po parent){
        if (parent != null) {
            child.setParentId(parent.getId());
            child.setLevel(parent.getLevel() + 1);

            child.setParentId1(parent.getParentId1());
            child.setParentId2(parent.getParentId2());
            child.setParentId3(parent.getParentId3());
            child.setParentId4(parent.getParentId4());
            child.setParentId5(parent.getParentId5());
            child.setParentId6(parent.getParentId6());
            child.setParentId7(parent.getParentId7());
            child.setParentId8(parent.getParentId8());
            child.setParentId9(parent.getParentId9());
            child.setParentId10(parent.getParentId10());
            switch (parent.getLevel()){
                case 1:
                    child.setParentId1(parent.getId());
                    break;
                case 2:
                    child.setParentId2(parent.getId());
                    break;
                case 3:
                    child.setParentId3(parent.getId());
                    break;
                case 4:
                    child.setParentId4(parent.getId());
                    break;
                case 5:
                    child.setParentId5(parent.getId());
                    break;
                case 6:
                    child.setParentId6(parent.getId());
                    break;
                case 7:
                    child.setParentId7(parent.getId());
                    break;
                case 8:
                    child.setParentId8(parent.getId());
                    break;
                case 9:
                    child.setParentId9(parent.getId());
                    break;
                case 10:
                    child.setParentId10(parent.getId());
                    break;
                default:
                    throw ExceptionTools.newRE("最大支持的树深度为" + maxLevel);
            }
        }
        return child;
    }
    /**
     * 创建子节点
     * @param entity
     * @param parentId
     * @return
     */
    default Po createChild(Po entity,String parentId){
        entity = initParentIdXByParent(entity, parentId);

        return create(entity);
    }
}
