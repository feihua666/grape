package grape.base.rest.dict.mapper;

import grape.base.service.dict.po.Dict;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.dict.form.DictCreateForm;
import grape.base.rest.dict.form.DictUpdateForm;
import grape.base.rest.dict.form.DictListPageForm;
import grape.base.rest.dict.vo.DictVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictWebMapper extends WebMapper<DictVo, Dict> {

    Dict formToPo(DictCreateForm f);
    Dict formToPo(DictUpdateForm f);
    Dict formToPo(DictListPageForm f);
}
