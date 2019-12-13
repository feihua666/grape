package grape.base.service.post.impl;

import grape.base.service.job.api.IJobService;
import grape.base.service.job.po.Job;
import grape.base.service.joblevel.po.JobLevel;
import grape.base.service.post.api.IPostService;
import grape.base.service.post.po.Post;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PostServiceImplTest implements ToolService {

    @Autowired
    private IJobService iJobService;
    @Autowired
    private IPostService iPostService;

    @Test
    public void testInsert() throws BadHanyuPinyinOutputFormatCombination {
        // key=职务名称 value为对应岗位的前缀
        Map<String, String[]> map = new HashMap<>();
        map.put("董事长",new String[]{""});
        map.put("总裁",new String[]{""});
        map.put("总经理",new String[]{"营销中心","运营"});
        map.put("总监",new String[]{"营销中心","运营","区","财务","培训中心","客服中心","呼叫中心","研发中心","数据中心"});
        map.put("经理",new String[]{"营销中心","运营","区","财务","培训中心","客服中心","呼叫中心","研发中心","数据中心"});
        map.put("主管",new String[]{"营销中心","运营","区","财务","培训中心","客服中心","呼叫中心","研发中心","数据中心"});
        map.put("主任",new String[]{""});
        map.put("工程师",new String[]{"测试","java开发","php开发","数据库管理","c#","UI","见习WEB前端开发","网络","IOS","安卓"});
        map.put("顾问",new String[]{"法务","海外","实习","总裁IT","金融IT","保险"});
        map.put("助理",new String[]{"总经理","运维","项目","招聘","设计","经理","部门"});

        // 部门类型字典id 普通部门
        String typeDictId = "1189011185556037634";
        List<Job> jobList = iJobService.list();
        for (Job job : jobList) {
            String prefix[] = map.get(job.getName());
            for (String pre : prefix) {
                Post post = new Post();
                post.setName(pre + job.getName());
                post.setJobId(job.getId());
                post.setCode(getPinyin(post.getName()).getFull());
                post.setIsPublic(true);
                post.setTypeDictId(typeDictId);
                post.setIsDisabled(false);
                iPostService.create(post);
            }

        }
    }
}