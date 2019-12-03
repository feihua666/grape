package grape.base.service.area.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.area.api.IAreaService;
import grape.base.service.area.po.Area;
import grape.base.service.dict.po.Dict;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AreaServiceImplTest {

    @Autowired
    IAreaService iAreaService;
    @Test
    public void test(){

        String areaId = "1190110313816027138";
        Area comp = iAreaService.getById(areaId);
        List<Area> list = iAreaService.list(Wrappers.<Area>query().and((qw)->
                qw.eq(Area.COLUMN_PARENT_ID + comp.getLevel(),areaId)
                        .or()
                        .eq(Area.COLUMN_ID,areaId)
        ));

        System.out.println(list);
        list.stream().map((i)->i.getName()).forEach(System.out::println);
    }
}