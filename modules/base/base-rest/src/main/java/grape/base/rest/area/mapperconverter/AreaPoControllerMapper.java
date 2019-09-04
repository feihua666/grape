package grape.base.rest.area.mapperconverter;

import grape.base.service.area.po.AreaPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.area.form.AreaPoCreateForm;
import grape.base.rest.area.form.AreaPoUpdateForm;
import grape.base.rest.area.form.AreaPoListPageForm;
import grape.base.rest.area.vo.AreaPoVo;
/**
 * <p>
 * 区域表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Mapper(componentModel = "spring")
public interface AreaPoControllerMapper extends ControllerMapperConverter<AreaPoVo, AreaPo, AreaPoCreateForm,AreaPoUpdateForm,AreaPoListPageForm> {

}
