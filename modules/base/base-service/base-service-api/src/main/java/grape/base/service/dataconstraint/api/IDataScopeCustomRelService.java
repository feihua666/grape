package grape.base.service.dataconstraint.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.service.common.IBaseRelService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据范围约束自定义表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
public interface IDataScopeCustomRelService extends IBaseRelService<DataScopeCustomRel> {

    /**
     * 判断数据范围是否设置自定义数据
     * @param dataScopeId
     * @return
     */
    default public boolean existByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId 不能为空");
        int r =count(Wrappers.<DataScopeCustomRel>lambdaQuery().eq(DataScopeCustomRel::getDataScopeId, dataScopeId));
        return r > 0;
    }


    /**
     * 获取数据范围绑定的绑定的自定义数据
     * @param dataScopeId
     * @return
     */
    default List<DataScopeCustomRel> getByDataScopeId(String dataScopeId){
        if (dataScopeId == null) {
            return null;
        }
        return list(Wrappers.<DataScopeCustomRel>lambdaQuery().eq(DataScopeCustomRel::getDataScopeId,dataScopeId));
    }

    /**
     * 清空数据范围下的功能
     * @param dataScopeId
     * @return
     */
    default boolean removeByDataScopeId(String dataScopeId){
        assertParamNotEmpty(dataScopeId,"dataScopeId is not empty");
        return remove(Wrappers.<DataScopeCustomRel>lambdaQuery().eq(DataScopeCustomRel::getDataScopeId, dataScopeId));
    }
    /**
     * 数据范围绑定功能
     * @param dataScopeId 数据范围id
     * @param checkedFuncIds 已选择的功能id
     * @param uncheckedFuncIds 未选择的功能id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载
     */
    default void dataScopeAssignData(String dataScopeId, List<String> checkedFuncIds,List<String> uncheckedFuncIds,Boolean isLazyLoad){

        removeAssignRel(dataScopeId,checkedFuncIds,uncheckedFuncIds,isLazyLoad,DataScopeCustomRel::getDataScopeId,DataScopeCustomRel::getDataId);
        // 再添加
        doBind(dataScopeId, checkedFuncIds, true);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param isDataScopeMain
     */
    default void doBind(String mainId,List<String> checkedIds,boolean isDataScopeMain){
        if (!isListEmpty(checkedIds)) {
            List<DataScopeCustomRel> insert = new ArrayList<>(checkedIds.size());
            DataScopeCustomRel dataScopeCustomRel = null;
            for (String checkedId : checkedIds) {
                dataScopeCustomRel = new DataScopeCustomRel();
                if (isDataScopeMain) {
                    dataScopeCustomRel.setDataScopeId(mainId);
                    dataScopeCustomRel.setDataId(checkedId);
                }else {
                    dataScopeCustomRel.setDataScopeId(checkedId);
                    dataScopeCustomRel.setDataId(mainId);
                }
                insert.add(dataScopeCustomRel);
            }
            saveBatch(insert);
        }
    }
}
