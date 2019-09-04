package grape.base.rest.dict.mapperconverter;

import grape.base.service.dict.po.DictPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.dict.form.DictPoCreateForm;
import grape.base.rest.dict.form.DictPoUpdateForm;
import grape.base.rest.dict.form.DictPoListPageForm;
import grape.base.rest.dict.vo.DictPoVo;
/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Mapper(componentModel = "spring")
public interface DictPoControllerMapper extends ControllerMapperConverter<DictPoVo, DictPo, DictPoCreateForm,DictPoUpdateForm,DictPoListPageForm> {

}
