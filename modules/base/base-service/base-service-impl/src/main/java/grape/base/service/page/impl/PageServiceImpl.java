package grape.base.service.page.impl;

import grape.base.service.page.po.Page;
import grape.base.service.page.mapper.PageMapper;
import grape.base.service.page.api.IPageService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Service
public class PageServiceImpl extends BaseServiceImpl<PageMapper, Page> implements IPageService {

}
