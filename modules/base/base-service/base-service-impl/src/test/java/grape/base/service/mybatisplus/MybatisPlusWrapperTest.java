package grape.base.service.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/12/19 9:50
 */
@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private IUserService iUserService;
    @Test
    public void test(){
        String sqlSegment = "and id=0 and nickname='xxx'";

        List<String> list = new ArrayList<>();
        list.add(sqlSegment);
        list.add(sqlSegment);
        list.add(sqlSegment);

        QueryWrapper<User> apply = Wrappers.<User>query().and(wq->
                {
                    //wq.apply(sqlSegment);
                    for (String s : list) {
                        wq.or(wqq -> wqq.apply(s));
                    }
                    return wq;
                }
        );
        List<User> listUser = iUserService.list(apply);
        listUser.stream().map(i->i.getNickname()).forEach(System.out::println);
    }

}
