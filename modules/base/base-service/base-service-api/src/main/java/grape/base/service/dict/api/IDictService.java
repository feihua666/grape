package grape.base.service.dict.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.po.Dict;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.common.IDictGroup;
import grape.common.service.trans.ITransService;

import java.util.List;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IDictService extends IBaseTreeService<Dict> , ITransService<String,String> {
    public static final String trans_dictName = "dictName";
    public static final String trans_dictCode = "dictCode";
    public static final String trans_dictParentName = "dictParentName";

    /**
     * 根据组编码查询字典项
     * @param groupCode 字典表中is_group = 1的字典组编码
     * @param dictQuery null忽略该条件
     * @return 组下的字典项
     */
    List<Dict> getItemByGroupCode(IDictGroup groupCode, Dict dictQuery);

    List<Dict> getItemByGroupCode(String groupCode, Dict dictQuery);

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        assertParamNotEmpty(code,"code 不能为空");

        Dict dict = new Dict();
        dict.setCode(code);
        int r = count(Wrappers.query(dict));
        return r > 0;
    }
    default Dict getByCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        Dict dict = new Dict();
        dict.setCode(code);
        return getOne(Wrappers.query(dict));
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_dictCode, trans_dictName, trans_dictParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_dictParentName)) {
            Dict parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else{
            Dict po = getById(key);
            if (po != null) {
                if (isEqual(type,trans_dictName)) {
                    return po.getName();

                }else if (isEqual(type,trans_dictCode)) {
                    return po.getCode();
                }
            }

        }
        return null;
    }
}
