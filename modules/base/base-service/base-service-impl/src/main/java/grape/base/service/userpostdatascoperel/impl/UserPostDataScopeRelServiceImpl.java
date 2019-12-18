package grape.base.service.userpostdatascoperel.impl;

import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.base.service.userpostdatascoperel.mapper.UserPostDataScopeRelMapper;
import grape.base.service.userpostdatascoperel.api.IUserPostDataScopeRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户岗位数据范围关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class UserPostDataScopeRelServiceImpl extends BaseServiceImpl<UserPostDataScopeRelMapper, UserPostDataScopeRel> implements IUserPostDataScopeRelService {


    @Autowired
    private DataScopeMapper dataScopeMapper;

    @Override
    public void doBind(String mainId, List<String> checkedIds, boolean isDataScopeMain) {
        if (!isEmpty(checkedIds)) {
            // 插入数据对象id
            Map<String, String> dataObjectMap = new HashMap<>();
            if (isDataScopeMain) {
                DataScope dataScope = dataScopeMapper.selectById(mainId);
                dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
            }else {
                List<DataScope> dataScopes =  dataScopeMapper.selectBatchIds(checkedIds);
                if (!isEmpty(dataScopes)) {
                    for (DataScope dataScope : dataScopes) {
                        dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
                    }
                }
            }
            List<UserPostDataScopeRel> insert = new ArrayList<>(checkedIds.size());
            UserPostDataScopeRel rel = null;
            for (String checkedId : checkedIds) {
                rel = new UserPostDataScopeRel();
                if (isDataScopeMain) {
                    rel.setDataScopeId(mainId);
                    rel.setUserPostId(checkedId);
                    rel.setDataObjectId(dataObjectMap.get(mainId));
                }else {
                    rel.setDataScopeId(checkedId);
                    rel.setUserPostId(mainId);
                    rel.setDataObjectId(dataObjectMap.get(checkedId));
                }
                insert.add(rel);
            }
            saveBatch(insert);
        }
    }
}
