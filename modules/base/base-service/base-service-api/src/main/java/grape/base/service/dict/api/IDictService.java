package grape.base.service.dict.api;

import grape.base.service.dict.po.Dict;
import grape.common.service.IBaseService;
import grape.common.service.IBaseTreeService;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
public interface IDictService extends IBaseTreeService<Dict> {

}
