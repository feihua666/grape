package grape.base.service.roledatascoperel.impl;

import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.roledatascoperel.po.RoleDataScopeRel;
import grape.base.service.roledatascoperel.mapper.RoleDataScopeRelMapper;
import grape.base.service.roledatascoperel.api.IRoleDataScopeRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色数据范围关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class RoleDataScopeRelServiceImpl extends BaseServiceImpl<RoleDataScopeRelMapper, RoleDataScopeRel> implements IRoleDataScopeRelService {

    @Autowired
    private IDataScopeService iDataScopeService;

    @Override
    public void doBind(String mainId, List<String> checkedIds, boolean isRoleMain) {
        if (!isListEmpty(checkedIds)) {
            // 插入数据对象id
            Map<String, String> dataObjectMap = new HashMap<>();
            if (isRoleMain) {
                List<DataScope> dataScopes = (List<DataScope>) iDataScopeService.listByIds(checkedIds);
                if (!isListEmpty(dataScopes)) {
                    for (DataScope dataScope : dataScopes) {
                        dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
                    }
                }
            }else {
                DataScope dataScope = iDataScopeService.getById(mainId);
                dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
            }
            List<RoleDataScopeRel> insert = new ArrayList<>(checkedIds.size());
            RoleDataScopeRel roleDataScopeRel = null;
            for (String checkedId : checkedIds) {
                roleDataScopeRel = new RoleDataScopeRel();
                if (isRoleMain) {
                    roleDataScopeRel.setRoleId(mainId);
                    roleDataScopeRel.setDataScopeId(checkedId);
                    roleDataScopeRel.setDataObjectId(dataObjectMap.get(checkedId));
                }else {
                    roleDataScopeRel.setRoleId(checkedId);
                    roleDataScopeRel.setDataScopeId(mainId);
                    roleDataScopeRel.setDataObjectId(dataObjectMap.get(mainId));

                }
                insert.add(roleDataScopeRel);
            }
            saveBatch(insert);
        }
    }
}
