package grape.base.service.impl.test;

import grape.base.service.dict.api.IDictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/7/19 15:07
 */

@SpringBootTest
public class BaseServiceApplicationTest {

    @Autowired
    IDictService iDictService;

    @Test
    public void test(){
        System.out.println(iDictService.list());
    }
}
