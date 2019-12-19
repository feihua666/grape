package grape.common.service.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import grape.common.service.po.IDBasePo;
import grape.common.tools.ToolService;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 所有service接口的父接口，以实现通用功能
 * Created by yangwei
 * Created at 2019/7/23 14:30
 */
public interface IBaseService<Po extends IDBasePo<?,?>> extends IService<Po>, ToolService {

    /**
     * 保存实体，操作同com.baomidou.mybatisplus.extension.service.IService#save(java.lang.Object)
     * 之所以加这个是兼容rpc的远程调用，controller调用service时建议使用该方法插入数据，以获取插入后的实体数据id
     *
     * @param entity
     * @return 返回插入后的实体
     */
    Po create(Po entity);

    /**
     * 提取po们的id
     * @param pos
     * @return
     */
    default List<String> convertIds(List<Po> pos){
        if (!isEmpty(pos)) {
            return pos.stream().map((p) -> p.getId().toString()).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 根据id查询，附加额外条件
     * @param ids
     * @param query
     * @return
     */
    default List<Po> listByIds(Collection<String> ids,Po query){
        if (isEmpty(ids)) {
            return null;
        }
        return list(Wrappers.query(query).in(IDBasePo.COLUMN_ID, ids));
    }
    /**
     * 分页查询的默认接口方法
     * @param page
     * @param query
     * @return
     */
    default IPage<Po> page(IPage<Po> page, Po query){
        return page(page, Wrappers.query(query));
    }
    /**
     * 分页查询的默认接口方法,带数据约束
     * @param page
     * @param query
     * @param constraintContents 能直接使用的约束sql
     * @return
     */
    default IPage<Po> page(IPage<Po> page, Po query,List<ConstraintContent> constraintContents){
        return page(page, Wrappers.query(query).and(wq->
                {
                    //wq.apply(sqlSegment);
                    for (ConstraintContent constraintContent : constraintContents) {
                        wq.or(wqq -> wqq.apply(constraintContent.getCompiledSqlContent()));
                    }
                    return wq;
                }
        ));
    }
    /**
     * 不分页查询的默认接口方法
     * @param query
     * @return
     */
    default List<Po> list(Po query){
        return list(Wrappers.query(query));
    }
    /**
     * 不分页查询的默认接口方法,带数据约束
     * @param query
     * @param constraintContents 能直接使用的约束sql
     * @return
     */
    default List<Po> list(Po query,List<ConstraintContent> constraintContents){
        return list(Wrappers.query(query).and(wq->
                {
                    //wq.apply(sqlSegment);
                    for (ConstraintContent constraintContent : constraintContents) {
                        wq.or(wqq -> wqq.apply(constraintContent.getCompiledSqlContent()));
                    }
                    return wq;
                }
        ));
    }

    /**
     * 根据id删除，带数据约束
     * @param id
     * @param constraintContents
     * @return
     */
    default boolean removeById(String id,List<ConstraintContent> constraintContents){
        return remove(Wrappers.<Po>query().eq(IDBasePo.COLUMN_ID, id).and(wq->
                {
                    //wq.apply(sqlSegment);
                    for (ConstraintContent constraintContent : constraintContents) {
                        wq.or(wqq -> wqq.apply(constraintContent.getCompiledSqlContent()));
                    }
                    return wq;
                }
        ));
    }

    /**
     * 根据id查询，带数据约束
     * @param id
     * @param constraintContents
     * @return
     */
    default Po getById(String id,List<ConstraintContent> constraintContents){
        return getOne(Wrappers.<Po>query().eq(IDBasePo.COLUMN_ID, id).and(wq->
                {
                    //wq.apply(sqlSegment);
                    for (ConstraintContent constraintContent : constraintContents) {
                        wq.or(wqq -> wqq.apply(constraintContent.getCompiledSqlContent()));
                    }
                    return wq;
                }
        ));
    }

    /**
     *
     * @param po
     * @param constraintContent
     * @return
     */
    default boolean updateById(Po po,List<ConstraintContent> constraintContent){
        return update(po, Wrappers.<Po>query().eq(IDBasePo.COLUMN_ID, po.getId()));
    }

    /**
     * 计数
     * @param query
     * @return
     */
    default int count(Po query){
        return count(Wrappers.query(query));
    }
}
