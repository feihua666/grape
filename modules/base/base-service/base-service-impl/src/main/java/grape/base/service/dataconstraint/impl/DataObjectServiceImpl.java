package grape.base.service.dataconstraint.impl;

import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.mapper.DataObjectMapper;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据对象表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class DataObjectServiceImpl extends BaseServiceImpl<DataObjectMapper, DataObject> implements IDataObjectService {

    @Autowired
    private DataScopeMapper dataScopeMapper;
    @Override
    public List<DataObject> getByDataScopeIds(List<String> dataScopeIds) {
        if (isListEmpty(dataScopeIds)) {
            return null;
        }

        List<DataScope> dataScopes = dataScopeMapper.selectBatchIds(dataScopeIds);
        if (isListEmpty(dataScopes)) {
            return null;
        }
        List<String> dataObjectIds = dataScopes.stream().map(item -> item.getDataObjectId()).collect(Collectors.toList());
        return (List<DataObject>) listByIds(dataObjectIds);
    }
}
