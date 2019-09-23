package grape.base.service.page.impl;

import grape.base.service.page.api.IPageService;
import grape.base.service.page.po.Page;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created by yangwei
 * Created at 2019/9/23 10:38
 */
@SpringBootTest
class PageServiceImplTest {

    @Autowired
    IPageService iPageService;

    @Test
    public void testInsert(){
        Page page = new Page();
        page.setCode("test");
        page.setName("测试");
        page.setSeq(10);
        boolean insert = iPageService.save(page);
        Assert.assertTrue(insert);
        if(insert){
            boolean delete = iPageService.removeById(page.getId());
            Assert.assertTrue(delete);
        }
    }
    @Test
    public void testUpdate(){
        Page page = new Page();
        page.setCode("test");
        page.setName("测试");
        page.setSeq(10);
        boolean insert = iPageService.save(page);
        Assert.assertTrue(insert);

        page.setCode("testUpdate");
        boolean update = iPageService.updateById(page);
        Assert.assertTrue(update);
        Page dbpage = iPageService.getById(page.getId());
        Assert.assertEquals("testUpdate",dbpage.getCode());
        Assert.assertEquals(new Integer(2),dbpage.getVersion());

        if(insert){
            boolean delete = iPageService.removeById(page.getId());
            Assert.assertTrue(delete);
        }
    }
}