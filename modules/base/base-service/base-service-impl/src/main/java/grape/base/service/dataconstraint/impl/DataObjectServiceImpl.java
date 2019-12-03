package grape.base.service.dataconstraint.impl;

import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.mapper.DataObjectMapper;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

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

}
