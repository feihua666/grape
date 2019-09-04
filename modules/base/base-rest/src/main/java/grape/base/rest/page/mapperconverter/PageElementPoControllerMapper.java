package grape.base.rest.page.mapperconverter;

import grape.base.service.page.po.PageElementPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;
import grape.base.rest.page.form.PageElementPoCreateForm;
import grape.base.rest.page.form.PageElementPoUpdateForm;
import grape.base.rest.page.form.PageElementPoListPageForm;
import grape.base.rest.page.vo.PageElementPoVo;
/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Mapper(componentModel = "spring")
public interface PageElementPoControllerMapper extends ControllerMapperConverter<PageElementPoVo, PageElementPo, PageElementPoCreateForm,PageElementPoUpdateForm,PageElementPoListPageForm> {

}
