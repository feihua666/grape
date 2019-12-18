package grape.base.service.userdatascoperel.impl;

import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
import grape.base.service.userdatascoperel.mapper.UserDataScopeRelMapper;
import grape.base.service.userdatascoperel.api.IUserDataScopeRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户数据范围关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class UserDataScopeRelServiceImpl extends BaseServiceImpl<UserDataScopeRelMapper, UserDataScopeRel> implements IUserDataScopeRelService {


    @Autowired
    private DataScopeMapper dataScopeMapper;

    @Override
    public void doBind(String mainId,List<String> checkedIds,boolean isDataScopeMain){
        if (!isEmpty(checkedIds)) {
            // 插入数据对象id
            Map<String, String> dataObjectMap = new HashMap<>();
            if (isDataScopeMain) {
                DataScope dataScope = dataScopeMapper.selectById(mainId);
                dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
            }else {
                List<DataScope> dataScopes = dataScopeMapper.selectBatchIds(checkedIds);
                if (!isEmpty(dataScopes)) {
                    for (DataScope dataScope : dataScopes) {
                        dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
                    }
                }
            }

            List<UserDataScopeRel> insert = new ArrayList<>(checkedIds.size());
            UserDataScopeRel rel = null;
            for (String checkedId : checkedIds) {
                rel = new UserDataScopeRel();
                if (isDataScopeMain) {
                    rel.setDataScopeId(mainId);
                    rel.setUserId(checkedId);
                    rel.setDataObjectId(dataObjectMap.get(mainId));
                }else {
                    rel.setDataScopeId(checkedId);
                    rel.setUserId(mainId);
                    rel.setDataObjectId(dataObjectMap.get(checkedId));
                }
                insert.add(rel);
            }
            saveBatch(insert);
        }
    }
}
