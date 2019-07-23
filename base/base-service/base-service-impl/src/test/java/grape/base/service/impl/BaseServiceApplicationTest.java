package grape.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import grape.base.service.impl.mapper.BaseUserPoMapper;
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
    private BaseUserPoMapper baseUserPoMapper;
    @Test
    void contextLoads() {
        List list = baseUserPoMapper.selectList(new QueryWrapper<BaseUser>().select());

        System.out.println(list);
    }
}
