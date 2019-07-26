package grape.base.rest.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/7/24 20:22
 */
@SpringBootTest
public class BaseRestApplicationTest {
    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8080/base-user",null,Object.class);
    }
}
