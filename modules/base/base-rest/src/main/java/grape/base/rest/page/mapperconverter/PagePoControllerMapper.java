package grape.base.rest.page.mapperconverter;

import grape.base.service.page.po.PagePo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.page.form.PagePoCreateForm;
import grape.base.rest.page.form.PagePoUpdateForm;
import grape.base.rest.page.form.PagePoListPageForm;
import grape.base.rest.page.vo.PagePoVo;
/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Mapper(componentModel = "spring")
public interface PagePoControllerMapper extends ControllerMapperConverter<PagePoVo, PagePo, PagePoCreateForm,PagePoUpdateForm,PagePoListPageForm> {

}
