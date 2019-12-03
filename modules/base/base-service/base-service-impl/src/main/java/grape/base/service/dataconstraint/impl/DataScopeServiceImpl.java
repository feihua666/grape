package grape.base.service.dataconstraint.impl;

import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据范围约束表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class DataScopeServiceImpl extends BaseServiceImpl<DataScopeMapper, DataScope> implements IDataScopeService {

}
