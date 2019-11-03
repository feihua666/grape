package grape.base.service;

import grape.base.service.area.api.IAreaService;
import grape.base.service.area.mapper.AreaMapper;
import grape.base.service.area.po.Area;
import grape.common.tools.PinyinDto;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2019/7/19 15:07
 */

@SpringBootTest
public class BaseServiceApplicationTest {

    @Autowired
    IAreaService iAreaService;
    @Autowired
    AreaMapper areaMapper;

    public static Map<String, String> ids = new HashMap<>();

    static {
        ids.put("country", "1189838938333253633");
        ids.put("province", "1189838482257219585");
        ids.put("city", "1189838538121154562");
        ids.put("district", "1189838597441196034");
    }

    @Test
    public void test() throws BadHanyuPinyinOutputFormatCombination {

        PinyinDto pinyin = iAreaService.getPinyin("sss你好ss");

    }
}