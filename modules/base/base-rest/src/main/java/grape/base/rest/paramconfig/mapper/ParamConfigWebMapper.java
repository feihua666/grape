package grape.base.rest.paramconfig.mapper;

import grape.base.service.paramconfig.po.ParamConfig;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.paramconfig.form.ParamConfigCreateForm;
import grape.base.rest.paramconfig.form.ParamConfigUpdateForm;
import grape.base.rest.paramconfig.form.ParamConfigListPageForm;
import grape.base.rest.paramconfig.vo.ParamConfigVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 参数配置表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParamConfigWebMapper extends WebMapper<ParamConfigVo, ParamConfig> {

    ParamConfig formToPo(ParamConfigCreateForm f);
    ParamConfig formToPo(ParamConfigUpdateForm f);
    ParamConfig formToPo(ParamConfigListPageForm f);
}
