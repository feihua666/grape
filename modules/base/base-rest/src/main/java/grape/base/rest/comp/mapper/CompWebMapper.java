package grape.base.rest.comp.mapper;

import grape.base.rest.comp.form.CompCreateForm;
import grape.base.rest.comp.form.CompListPageForm;
import grape.base.rest.comp.form.CompUpdateForm;
import grape.base.rest.comp.vo.CompVo;
import grape.base.service.comp.po.Comp;
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
public interface CompWebMapper extends WebMapper<CompVo, Comp> {

    Comp formToPo(CompCreateForm f);
    Comp formToPo(CompListPageForm f);
    Comp formToPo(CompUpdateForm f);
}
