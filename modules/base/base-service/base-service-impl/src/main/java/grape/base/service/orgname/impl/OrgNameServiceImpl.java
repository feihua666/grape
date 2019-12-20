package grape.base.service.orgname.impl;

import grape.base.service.orgname.po.OrgName;
import grape.base.service.orgname.mapper.OrgNameMapper;
import grape.base.service.orgname.api.IOrgNameService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织树名称表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Service
public class OrgNameServiceImpl extends BaseServiceImpl<OrgNameMapper, OrgName> implements IOrgNameService {

}
