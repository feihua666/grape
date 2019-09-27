package grape.base.service.dict.impl;

import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DictServiceImplTest {

    @Autowired
    IDictService iDictService;

    @Test
    public void testInsert(){
        Dict dict = new Dict();
        dict.setCode("test");
        dict.setName("测试");
        dict.setIsSystem(false);
        dict.setIsPublic(true);
        dict.setIsDisable(false);
        dict.setIsGroup(false);
        dict.setSeq(10);
        boolean insert = iDictService.save(dict);
        Assert.assertTrue(insert);
        if(insert){
            boolean delete = iDictService.removeById(dict.getId());
            Assert.assertTrue(delete);
        }
    }
    @Test
    public void testGetChildren(){
        List<Dict> children = iDictService.getChildren(1L);
        Assert.assertTrue(children.size() > 0);
    }
    @Test
    public void testGetChildrenCount(){
        int r = iDictService.getChildrenCount(1L);
        Assert.assertTrue(r > 0);
    }
    @Test
    public void testCreateChild(){
        Dict dict = new Dict();
        dict.setCode("test");
        dict.setName("测试");
        dict.setIsSystem(false);
        dict.setIsPublic(true);
        dict.setIsDisable(false);
        dict.setIsGroup(false);
        dict.setSeq(10);
        boolean insert = iDictService.save(dict);
        Assert.assertTrue(insert);

        Dict dictChild = new Dict();
        dictChild.setCode("testChild");
        dictChild.setName("测试子节点");
        dictChild.setIsSystem(false);
        dictChild.setIsPublic(true);
        dictChild.setIsDisable(false);
        dictChild.setIsGroup(false);
        dictChild.setSeq(10);
        dictChild = iDictService.createChild(dictChild,dict.getId());
        Assert.assertTrue(dictChild != null);
        Assert.assertTrue(dictChild.getLevel() == 2);
        boolean delete = iDictService.removeById(dict.getId());
        boolean deleteChild = iDictService.removeById(dictChild.getId());
        Assert.assertTrue(delete&& deleteChild);

    }
}