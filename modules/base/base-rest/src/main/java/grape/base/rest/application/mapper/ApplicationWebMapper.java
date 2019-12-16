package grape.base.rest.application.mapper;

import grape.base.rest.application.form.ApplicationListForm;
import grape.base.service.application.po.Application;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.application.form.ApplicationCreateForm;
import grape.base.rest.application.form.ApplicationUpdateForm;
import grape.base.rest.application.form.ApplicationListPageForm;
import grape.base.rest.application.vo.ApplicationVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 应用表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-13
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationWebMapper extends WebMapper<ApplicationVo, Application> {

    Application formToPo(ApplicationCreateForm f);
    Application formToPo(ApplicationUpdateForm f);
    Application formToPo(ApplicationListPageForm f);
    Application formToPo(ApplicationListForm f);
}
