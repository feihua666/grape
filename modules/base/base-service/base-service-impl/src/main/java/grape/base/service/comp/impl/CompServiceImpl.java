package grape.base.service.comp.impl;

import grape.base.service.comp.po.Comp;
import grape.base.service.comp.mapper.CompMapper;
import grape.base.service.comp.api.ICompService;
import grape.common.service.BaseServiceImpl;
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
public class CompServiceImpl extends BaseServiceImpl<CompMapper, Comp> implements ICompService {

}
