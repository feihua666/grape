package grape.base.service.user.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.comp.api.ICompService;
import grape.base.service.comp.po.Comp;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dept.po.Dept;
import grape.base.service.user.api.IUserService;
import grape.base.service.user.dto.UserCreateParam;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceImplTest implements ToolService {

    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;
    @Autowired
    private IUserService iUserService;

    // 默认密码123456
    String password = "43dbc7945d9ca6ca18ffb5483b3424d4370657d2";
    String salt = "dd42bab0d9f59821";
    // 性别
    String manDictId = "3";// 男 字典id
    String femaleDictId = "4";// 女 字典id
    //@Test
    public void testInsert() throws BadHanyuPinyinOutputFormatCombination {
        List<Dept> deptList = iDeptService.list();

        // 每个部门下生成1000个用户
        for (Dept dept : deptList) {
            for (int i = 0; i < 1000; i++) {
                UserCreateParam param = new UserCreateParam();
                param.setNickname(UserNameTool.getChineseName());

                param.setPassword(password);
                param.setSalt(salt);
                param.setAccount(getPinyin(param.getNickname()).getFull());
                param.setIsVirtual(true);
                param.setDeptId(dept.getId());
                if (RandomUtil.randomBoolean()) {
                    param.setGenderDictId(manDictId);
                }else {
                    param.setGenderDictId(femaleDictId);
                }
                // 可能随机帐号有重复，try一下
                try {
                    iUserService.createUser(param);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }

    }
}