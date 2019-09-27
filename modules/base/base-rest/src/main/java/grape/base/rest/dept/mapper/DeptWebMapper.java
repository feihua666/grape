package grape.base.rest.dept.mapper;

import grape.base.service.dept.po.Dept;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.dept.form.DeptCreateForm;
import grape.base.rest.dept.form.DeptUpdateForm;
import grape.base.rest.dept.form.DeptListPageForm;
import grape.base.rest.dept.vo.DeptVo;
/**
 * <p>
 * 部门表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring")
public interface DeptWebMapper extends WebMapper<DeptVo, Dept, DeptCreateForm,DeptUpdateForm,DeptListPageForm> {

}
