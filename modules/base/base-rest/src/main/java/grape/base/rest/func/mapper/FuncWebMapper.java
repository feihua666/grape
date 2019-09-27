package grape.base.rest.func.mapper;

import grape.base.service.func.po.Func;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.vo.FuncVo;
/**
 * <p>
 * 菜单功能表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Mapper(componentModel = "spring")
public interface FuncWebMapper extends WebMapper<FuncVo, Func, FuncCreateForm,FuncUpdateForm,FuncListPageForm> {

}
