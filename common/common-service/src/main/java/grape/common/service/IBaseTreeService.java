package grape.common.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.dto.TreeNodeDto;
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
    default List<Po> getChildren(Long parentId){
        if (parentId == null) {
            return null;
        }
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeBasePo.COLUMN_PARENT_ID,parentId);
        return (List<Po>) listByMap(p);
    }
    default List<Po> getAllChildren(Long parentId){
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
    default int getChildrenCount(Long parentId){
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
    default Po getParent(Long id){
        if (id == null) {
            return null;
        }
        return getById(id);
    }

    /**
     * 获取所有父级
     * @param id
     * @return
     */
    default List<Po> getAllParents(Long id){
        if (id == null) {
            return null;
        }
        Po dbPo = getParent(id);
        if (dbPo == null) {
            return null;
        }
        List<Long> parentIds = new ArrayList<>(maxLevel - 1);
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
     * 创建子节点
     * @param entity
     * @param parentId
     * @return
     */
    default Po createChild(Po entity,Long parentId){
        Po parent = getParent(parentId);
        if (parent != null) {
            entity.setParentId(parentId);
            entity.setLevel(parent.getLevel() + 1);

            entity.setParentId1(parent.getParentId1());
            entity.setParentId2(parent.getParentId2());
            entity.setParentId3(parent.getParentId3());
            entity.setParentId4(parent.getParentId4());
            entity.setParentId5(parent.getParentId5());
            entity.setParentId6(parent.getParentId6());
            entity.setParentId7(parent.getParentId7());
            entity.setParentId8(parent.getParentId8());
            entity.setParentId9(parent.getParentId9());
            entity.setParentId10(parent.getParentId10());
            switch (parent.getLevel()){
                case 1:
                    entity.setParentId1(parentId);
                    break;
                case 2:
                    entity.setParentId2(parentId);
                    break;
                case 3:
                    entity.setParentId3(parentId);
                    break;
                case 4:
                    entity.setParentId4(parentId);
                    break;
                case 5:
                    entity.setParentId5(parentId);
                    break;
                case 6:
                    entity.setParentId6(parentId);
                    break;
                case 7:
                    entity.setParentId7(parentId);
                    break;
                case 8:
                    entity.setParentId8(parentId);
                    break;
                case 9:
                    entity.setParentId9(parentId);
                    break;
                case 10:
                    entity.setParentId10(parentId);
                    break;
                default:
                    throw ExceptionTools.newRE("最大支持的树深度为" + maxLevel);
            }
        }

        return create(entity);
    }
}
