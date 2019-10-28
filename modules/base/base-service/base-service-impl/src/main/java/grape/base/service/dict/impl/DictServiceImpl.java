package grape.base.service.dict.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.po.Dict;
import grape.base.service.dict.mapper.DictMapper;
import grape.base.service.dict.api.IDictService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public List<Dict> getItemByGroupCode(String groupCode, Boolean isDisabled) {
        Dict dict = new Dict();
        dict.setCode(groupCode);
        dict.setIsDisabled(isDisabled);
        Dict groupDict = getOne(Wrappers.query(dict));
        if (groupDict != null) {
            dict.setCode(null);
            dict.setParentId(groupDict.getId());
            return list(Wrappers.query(dict));
        }
        return null;
    }

    @Override
    public List<Dict> getItemByGroupCode(String groupCode, Boolean isDisabled, List<String> itemCodes) {
        if (isListEmpty(itemCodes)) {
            return null;
        }
        List<Dict> dicts = getItemByGroupCode(groupCode, isDisabled);
        // 以下是去除不包括itemCodes的项
        if (!isListEmpty(dicts)) {
            dicts = dicts.stream().filter(dict -> itemCodes.contains(dict.getCode())).collect(Collectors.toList());
        }
        return dicts;
    }
}
