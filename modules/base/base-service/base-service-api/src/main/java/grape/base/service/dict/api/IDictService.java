package grape.base.service.dict.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.po.Dict;
import grape.base.service.func.po.Func;
import grape.common.exception.runtime.InvalidParamsException;
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

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Dict dict = new Dict();
        dict.setCode(code);
        int r = count(Wrappers.query(dict));
        return r > 0;
    }
}
