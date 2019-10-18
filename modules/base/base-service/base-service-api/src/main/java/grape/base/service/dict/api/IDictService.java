package grape.base.service.dict.api;

import grape.base.service.dict.po.Dict;
import grape.common.service.IBaseTreeService;

import java.util.List;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IDictService extends IBaseTreeService<Dict> {

    /**
     * 根据组编码查询字典项
     * @param groupCode 字典表中is_group = 1的字典组编码
     * @param isDisabled 禁用状态，null忽略该条件
     * @return 组下的字典项
     */
    List<Dict> getItemByGroupCode(String groupCode,Boolean isDisabled);

    /**
     * 根据组编码查询字典项
     * @param groupCode 字典表中is_group = 1的字典组编码
     * @param isDisabled 禁用状态，null忽略该条件
     * @param itemCode 字典项编码
     * @return
     */
    List<Dict> getItemByGroupCode(String groupCode,Boolean isDisabled,List<String> itemCode);
}
