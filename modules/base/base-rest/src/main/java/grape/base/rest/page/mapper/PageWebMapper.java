package grape.base.rest.page.mapper;

import grape.base.service.page.po.Page;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.page.form.PageCreateForm;
import grape.base.rest.page.form.PageUpdateForm;
import grape.base.rest.page.form.PageListPageForm;
import grape.base.rest.page.vo.PageVo;
/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Mapper(componentModel = "spring")
public interface PageWebMapper extends WebMapper<PageVo, Page, PageCreateForm,PageUpdateForm,PageListPageForm> {

}
