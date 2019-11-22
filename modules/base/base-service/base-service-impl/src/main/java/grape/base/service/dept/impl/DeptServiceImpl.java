package grape.base.service.dept.impl;

import grape.base.service.dept.po.Dept;
import grape.base.service.dept.mapper.DeptMapper;
import grape.base.service.dept.api.IDeptService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

}
