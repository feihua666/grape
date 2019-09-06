package grape.base.service.dict.impl;

import grape.base.service.dict.po.Dict;
import grape.base.service.dict.mapper.DictMapper;
import grape.base.service.dict.api.IDictService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

}
