package grape.base.service.job.impl;

import cn.hutool.core.util.RandomUtil;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.impl.UserNameTool;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JobServiceImplTest implements ToolService {

    @Autowired
    private IJobService iJobService;

    //@Test
    public void testInsert() throws BadHanyuPinyinOutputFormatCombination {

        // 类型普通
        String typeDictId = "1189091829384601602";
        String jobName[] = {
                "董事长",
                "总裁",
                "总经理",
                "总监",
                "经理",
                "主管",
                "主任",
                "工程师",
                "顾问",
                "助理"
        };
        for (String name : jobName) {
            Job job = new Job();
            job.setName(name);
            job.setTypeDictId(typeDictId);
            job.setIsPublic(true);
            job.setCode(getPinyin(name).getFull());
            iJobService.create(job);
        }
    }
}