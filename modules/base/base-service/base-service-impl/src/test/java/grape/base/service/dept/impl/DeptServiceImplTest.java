package grape.base.service.dept.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.dict.api.IDictService;
import grape.base.service.dict.po.Dict;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DeptServiceImplTest implements ToolService {

    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;

    @Test
    public void testInsert() throws BadHanyuPinyinOutputFormatCombination {

        List<String> compNames = new ArrayList<>();
        compNames.add("天津公司");
        compNames.add("杭州公司");
        compNames.add("成都公司");
        compNames.add("南京公司");
        List<String> depts = new ArrayList<>();
        depts.add("研发");
        depts.add("市场");
        depts.add("运维");
        depts.add("财务");
        depts.add("测试");
        List<Comp> compList = iCompService.list(Wrappers.<Comp>query().in("name", compNames));

        for (Comp comp : compList) {
            for (String deptName : depts) {
                Dept dept = new Dept();
                dept.setCode(comp.getCode().replace("comp", "") + getPinyin(deptName).getFull() + "_dept").setName(deptName + "部门");
                dept.setCompId(comp.getId());
                dept.setTypeDictId("6");
                dept.setIsVirtual(true);
                iDeptService.create(dept);
            }

        }

    }
}