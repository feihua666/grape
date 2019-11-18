package grape.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import grape.common.service.po.IDBasePo;
import grape.common.tools.ToolService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 关系表基础接口
 * 适用角色绑定用户等两个字段关系的处理
 * Created by yangwei
 * Created at 2019/7/23 14:30
 */
public interface IBaseRelService<Po extends IDBasePo<?,?>> extends IBaseService<Po> {
    /**
     * 关系处理
     * @param mainId 主id，如：如果是角色分配功能，则为角色id
     * @param checkedIds 已选择的被分配的id
     * @param uncheckeIds 未选择的被分配的id，如果页面为懒加载该参数必须传，否则认为全选
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载，如果不是懒加载会清空主id已绑定的所有数据
     */
    default void removeAssignRel(String mainId, List<String> checkedIds, List<String> uncheckeIds,Boolean isLazyLoad, SFunction<Po,?> main, SFunction<Po,?> other){

        assertParamNotEmpty(mainId,"mainId is not empty");

        if ((isLazyLoad!=null && isLazyLoad) || (isListEmpty(checkedIds) && isListEmpty(uncheckeIds))) {
            // 先删除
            remove(Wrappers.<Po>lambdaQuery().eq(main,mainId));
        }else {
            List<String> removedIds = new ArrayList<>();
            if(isListEmpty(checkedIds)){
                removedIds.addAll(checkedIds);
            }
            if(isListEmpty(uncheckeIds)){
                removedIds.addAll(uncheckeIds);
            }

            remove(Wrappers.<Po>lambdaQuery()
                    .eq(main, mainId)
                    .in(other, removedIds));
        }

    }

}
