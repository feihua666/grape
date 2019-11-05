package grape.base.rest.func.mapper;

import grape.base.service.func.po.Func;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.func.form.FuncCreateForm;
import grape.base.rest.func.form.FuncUpdateForm;
import grape.base.rest.func.form.FuncListPageForm;
import grape.base.rest.func.vo.FuncVo;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 * 菜单功能表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncWebMapper extends WebMapper<FuncVo, Func> {

    Func formToPo(FuncCreateForm f);
    Func formToPo(FuncUpdateForm f);
    Func formToPo(FuncListPageForm f);
}
