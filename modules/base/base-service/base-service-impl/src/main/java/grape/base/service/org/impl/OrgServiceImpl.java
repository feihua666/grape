package grape.base.service.org.impl;

import grape.base.service.org.po.Org;
import grape.base.service.org.mapper.OrgMapper;
import grape.base.service.org.api.IOrgService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织树表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements IOrgService {

}
