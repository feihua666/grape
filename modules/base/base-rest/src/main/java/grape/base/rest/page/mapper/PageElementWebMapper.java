package grape.base.rest.page.mapper;

import grape.base.service.page.po.PageElement;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.page.form.PageElementCreateForm;
import grape.base.rest.page.form.PageElementUpdateForm;
import grape.base.rest.page.form.PageElementListPageForm;
import grape.base.rest.page.vo.PageElementVo;
/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Mapper(componentModel = "spring")
public interface PageElementWebMapper extends WebMapper<PageElementVo, PageElement, PageElementCreateForm,PageElementUpdateForm,PageElementListPageForm> {

}
