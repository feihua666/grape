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
        System.out.println(pinyin);
        // List<Map<String,Object>> root = areaMapper.selectCopys("fcdab3b9fab611e794174439c4325934",null);
        //Area rootArea = warp(root.get(0));
        //iAreaService.create(rootArea);

        //createChild(rootArea,root.get(0));
    }

    void createChild(Area parent,Map<String,Object> map) throws BadHanyuPinyinOutputFormatCombination {
        List<Map<String,Object>> province = areaMapper.selectCopys(null,map.get("ID").toString());
        if (province == null) {
            return;
        }
        for (Map<String, Object> stringObjectMap : province) {
            Area provinceArea = warp(stringObjectMap);
            iAreaService.createChild(provinceArea, parent.getId());
            createChild(provinceArea,stringObjectMap);
        }
    }

    private Area warp(Map<String,Object> map) throws BadHanyuPinyinOutputFormatCombination {
        Area area = new Area();
        area.setLatitude(map.get("LATITUDE").toString());
        area.setName(map.get("NAME").toString());
        area.setLongitude(map.get("LONGITUDE").toString());
        area.setTypeDictId(ids.get(map.get("TYPE").toString()));
        area.setSeq(10);
        PinyinDto pinyin = iAreaService.getPinyin(area.getName(),true);
        area.setSpellFirst(pinyin.getFirst());
        area.setSpellSimple(pinyin.getSimple());
        area.setSpell(pinyin.getFull());
        return area;
    }
}
