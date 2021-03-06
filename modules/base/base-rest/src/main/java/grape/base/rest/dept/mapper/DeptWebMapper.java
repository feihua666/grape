package grape.base.rest.dept.mapper;

import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.vo.DeptVo;
import grape.base.service.dept.po.Dept;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 部门表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptWebMapper extends WebMapper<DeptVo, Dept> {
    Dept formToPo(DeptCreateForm f);
    Dept formToPo(DeptListPageForm f);
    Dept formToPo(DeptUpdateForm f);
}
