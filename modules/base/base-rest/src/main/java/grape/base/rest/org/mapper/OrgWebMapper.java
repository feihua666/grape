package grape.base.rest.org.mapper;

import grape.base.service.org.po.Org;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.org.form.OrgCreateForm;
import grape.base.rest.org.form.OrgUpdateForm;
import grape.base.rest.org.form.OrgListPageForm;
import grape.base.rest.org.vo.OrgVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 组织树表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrgWebMapper extends WebMapper<OrgVo, Org> {

    Org formToPo(OrgCreateForm f);
    Org formToPo(OrgUpdateForm f);
    Org formToPo(OrgListPageForm f);
}
