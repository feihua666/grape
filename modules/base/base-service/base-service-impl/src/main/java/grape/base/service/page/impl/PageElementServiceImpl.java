package grape.base.service.page.impl;

import grape.base.service.page.po.PageElementPo;
import grape.base.service.page.mapper.PageElementMapper;
import grape.base.service.page.api.IPageElementService;
import grape.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Service
public class PageElementServiceImpl extends BaseServiceImpl<PageElementMapper, PageElementPo> implements IPageElementService {

}
