package grape.base.service.joblevel.impl;

import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.joblevel.po.JobLevel;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JobLevelServiceImplTest implements ToolService {

    @Autowired
    private IJobService iJobService;
    @Autowired
    private IJobLevelService iJobLevelService;

    //@Test
    public void testInsert() throws BadHanyuPinyinOutputFormatCombination {

        String prefix[] = {"高级", "", "高级副","副"};

        List<Job> jobList = iJobService.list();
        for (Job job : jobList) {
            for (String pre : prefix) {
                JobLevel jobLevel = new JobLevel();
                jobLevel.setName(pre + job.getName());
                jobLevel.setJobId(job.getId());
                jobLevel.setCode(getPinyin(jobLevel.getName()).getFull());
                iJobLevelService.create(jobLevel);
            }

        }
    }
}