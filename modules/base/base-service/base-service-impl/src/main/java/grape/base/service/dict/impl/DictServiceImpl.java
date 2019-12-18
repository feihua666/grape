package grape.base.service.dict.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dict.po.Dict;
import grape.base.service.dict.mapper.DictMapper;
import grape.base.service.dict.api.IDictService;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.BaseServiceImpl;
import grape.common.service.common.DictService;
import grape.common.service.common.IDictGroup;
import org.springframework.stereotype.Service;

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
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService, DictService {

    @Override
    public List<Dict> getItemByGroupCode(IDictGroup groupCode, Dict dictQuery) {

        return getItemByGroupCode(groupCode.groupCode(), dictQuery);
    }

    @Override
    public List<Dict> getItemByGroupCode(String groupCode, Dict dictQuery) {
        assertParamNotEmpty(groupCode,"groupCode 不能为空");
        Dict dict = getByCode(groupCode);
        if (dict == null) {
            return null;
        }
        return list(Wrappers.query(dictQuery).lambda().eq(Dict::getParentId,dict.getId()));
    }

    @Override
    public String getCodeById(String id) {
        if(!isStrEmpty(id)){
            Dict dict = getById(id);
            if (dict != null) {
                return dict.getCode();
            }
        }
        return null;
    }
}
