package grape.base.rest.area.mapper;

import grape.base.service.area.po.Area;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.area.form.AreaCreateForm;
import grape.base.rest.area.form.AreaUpdateForm;
import grape.base.rest.area.form.AreaListPageForm;
import grape.base.rest.area.vo.AreaVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 区域表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AreaWebMapper extends WebMapper<AreaVo, Area> {

    Area formToPo(AreaCreateForm f);
    Area formToPo(AreaListPageForm f);
    Area formToPo(AreaUpdateForm f);
}
