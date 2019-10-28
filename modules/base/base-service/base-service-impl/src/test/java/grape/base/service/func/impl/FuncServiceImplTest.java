package grape.base.service.func.impl;

import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by yangwei
 * Created at 2019/9/23 16:58
 */
@SpringBootTest
class FuncServiceImplTest {
    @Autowired
    IFuncService iFuncService;
    @Test
    public void testInsert(){
        Func func = new Func();
        func.setCode("test");
        func.setName("测试");
        func.setIsDisabled(false);
        func.setTypeDictId("11");
        func.setSeq(10);
        boolean insert = iFuncService.save(func);
        Assert.assertTrue(insert);
        if(insert){
            boolean delete = iFuncService.removeById(func.getId());
            Assert.assertTrue(delete);
        }
    }
}