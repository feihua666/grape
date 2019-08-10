package grape.base.service.impl.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import grape.base.service.api.po.BaseUserPo;
import grape.base.service.impl.mapper.BaseUserMapper;
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
    private BaseUserMapper baseUserMapper;
    @Test
    void contextLoads() {
        List list = baseUserMapper.selectList(new QueryWrapper<BaseUserPo>().select());

        System.out.println(list);
    }
}
