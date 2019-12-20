package grape.base.rest.orgname.mapper;

import grape.base.rest.orgname.form.OrgNameListForm;
import grape.base.service.orgname.po.OrgName;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.orgname.form.OrgNameCreateForm;
import grape.base.rest.orgname.form.OrgNameUpdateForm;
import grape.base.rest.orgname.form.OrgNameListPageForm;
import grape.base.rest.orgname.vo.OrgNameVo;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 组织树名称表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrgNameWebMapper extends WebMapper<OrgNameVo, OrgName> {

    OrgName formToPo(OrgNameCreateForm f);
    OrgName formToPo(OrgNameUpdateForm f);
    OrgName formToPo(OrgNameListPageForm f);
    OrgName formToPo(OrgNameListForm f);
}
