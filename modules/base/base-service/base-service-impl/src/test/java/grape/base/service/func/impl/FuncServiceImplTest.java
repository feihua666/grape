package grape.base.service.func.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.func.api.IFuncService;
import grape.base.service.func.po.Func;
import grape.common.tools.ToolService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by yangwei
 * Created at 2019/9/23 16:58
 */
@SpringBootTest
class FuncServiceImplTest implements ToolService {
    @Autowired
    IFuncService iFuncService;
    @Test
    public void testInsert(){
        Func func = new Func();
        func.setCode("test");
        func.setName("测试");
        func.setIsDisabled(false);
        func.setTypeDictId("11");
        func.setSeq(10);
        boolean insert = iFuncService.save(func);
        Assert.assertTrue(insert);
        if(insert){
            boolean delete = iFuncService.removeById(func.getId());
            Assert.assertTrue(delete);
        }
    }
    //@Test
    public void testInsert1() throws BadHanyuPinyinOutputFormatCombination {
        String type_tree = "tree";
        String type_rel = "rel";
        String buttonDictId = "18";
        List< Map<String, String>> tablesConfig = new ArrayList<>();
        Map<String, String> item1 = new HashMap<>();
        Map<String, String> item2 = new HashMap<>();
        Map<String, String> item3 = new HashMap<>();
        Map<String, String> item4 = new HashMap<>();
        Map<String, String> item5 = new HashMap<>();
        Map<String, String> item6 = new HashMap<>();
        Map<String, String> item7 = new HashMap<>();
        Map<String, String> item8 = new HashMap<>();
        Map<String, String> item9 = new HashMap<>();
        Map<String, String> item10 = new HashMap<>();
        Map<String, String> item11 = new HashMap<>();
        Map<String, String> item12 = new HashMap<>();
        Map<String, String> item13 = new HashMap<>();
        Map<String, String> item14 = new HashMap<>();
        Map<String, String> item15 = new HashMap<>();
        Map<String, String> item16 = new HashMap<>();
        Map<String, String> item17 = new HashMap<>();
        Map<String, String> item18 = new HashMap<>();
        Map<String, String> item19 = new HashMap<>();
        Map<String, String> item20 = new HashMap<>();
        Map<String, String> item21 = new HashMap<>();
        Map<String, String> item22 = new HashMap<>();
        Map<String, String> item23 = new HashMap<>();
        Map<String, String> item24 = new HashMap<>();
        Map<String, String> item25 = new HashMap<>();




        item1.put("name", "base_application");
        item1.put("namecn", "应用");
        item1.put("noPageQuery", "noPageQuery");

        item2.put("name", "base_area");
        item2.put("namecn", "区域");
        item2.put("type", type_tree);

        item3.put("name", "base_comp");
        item3.put("namecn", "公司");
        item3.put("type", type_tree);

        item4.put("name", "base_data_object");
        item4.put("namecn", "数据对象");
        item4.put("noPageQuery", "noPageQuery");

        item5.put("name", "base_data_scope");
        item5.put("namecn", "数据范围约束");

        //item6.put("name", "base_data_scope_custom_rel");
        //item6.put("namecn", "数据范围约束自定义");

        item7.put("name", "base_dept");
        item7.put("namecn", "部门");
        item7.put("type", type_tree);

        item8.put("name", "base_dict");
        item8.put("namecn", "字典");
        item8.put("type", type_tree);
        item8.put("enable", "enable");

        item9.put("name", "base_func");
        item9.put("namecn", "功能");
        item9.put("type", type_tree);
        item9.put("enable", "enable");

        item10.put("name", "base_job");
        item10.put("namecn", "职务");
        item10.put("noPageQuery", "noPageQuery");

        item11.put("name", "base_job_level");
        item11.put("namecn", "职级");
        item11.put("noPageQuery", "noPageQuery");

        item12.put("name", "base_param_config");
        item12.put("namecn", "参数配置");
        item12.put("enable", "enable");

        item13.put("name", "base_post");
        item13.put("namecn", "岗位");
        item13.put("noPageQuery", "noPageQuery");
        item13.put("enable", "enable");

        //item14.put("name", "base_post_data_scope_rel");

        item15.put("name", "base_role");
        item15.put("namecn", "角色");
        item15.put("type", type_tree);
        item15.put("enable", "enable");

        //item16.put("name", "base_role_data_scope_rel");
        //item17.put("name", "base_role_func_rel");

        item18.put("name", "base_user");
        item18.put("namecn", "用户");
        item18.put("noPageQuery", "noPageQuery");
        item18.put("enable", "enable");

        //item19.put("name", "base_user_data_scope_rel");
        //item20.put("name", "base_user_identifier");
        //item20.put("namecn", "用户登录标识");
        //item20.put("enable", "enable");

        item21.put("name", "base_user_post");
        item21.put("namecn", "用户岗位");
        //item22.put("name", "base_user_post_data_scope_rel");
        //item23.put("name", "base_user_post_role_rel");
        //item24.put("name", "base_user_pwd");
        //item24.put("namecn", "用户密码");
        //item25.put("name", "base_user_role_rel");

        tablesConfig.add(item1);
        tablesConfig.add(item2);
        tablesConfig.add(item3);
        tablesConfig.add(item4);
        tablesConfig.add(item5);
        tablesConfig.add(item6);
        tablesConfig.add(item7);
        tablesConfig.add(item8);
        tablesConfig.add(item9);
        tablesConfig.add(item10);
        tablesConfig.add(item11);
        tablesConfig.add(item12);
        tablesConfig.add(item13);
        tablesConfig.add(item14);
        tablesConfig.add(item15);
        tablesConfig.add(item16);
        tablesConfig.add(item17);
        tablesConfig.add(item18);
        tablesConfig.add(item19);
        tablesConfig.add(item20);
        tablesConfig.add(item21);
        tablesConfig.add(item22);
        tablesConfig.add(item23);
        tablesConfig.add(item24);
        tablesConfig.add(item25);
        List<Func> tobeinsertList = new ArrayList<>();
        for (Map<String, String> map : tablesConfig) {
            if (map.isEmpty()) {
                continue;
            }
            String namecn = map.get("namecn");
            String name = map.get("name");
            String permissionPre = name.replace("base_", "").replace("_", "");
            Func func = iFuncService.getOne(Wrappers.<Func>lambdaQuery().eq(Func::getName,  namecn+ "管理"));

            String []api = {"添加","修改","删除","分页列表","不分页列表","启用或禁用","懒加载树"};
            for (int i = 0; i < api.length; i++) {
                String str = api[i];
                Func funcInsert = new Func();
                funcInsert.setIsDisabled(false);
                funcInsert.setSeq(10);
                funcInsert.setApplicationId(func.getApplicationId());

                funcInsert.setTypeDictId(buttonDictId);
                funcInsert = iFuncService.initParentIdXByParent(funcInsert, func.getId());
                if(isEqual("添加",str)){
                    funcInsert.setName(str + namecn);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());
                    funcInsert.setPermissions(permissionPre + ":single:create");
                }
                if(isEqual("修改",str)){
                    funcInsert.setName(str + namecn);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());
                    funcInsert.setPermissions(permissionPre + ":single:updateById" + "," + permissionPre + ":single:queryById");
                }
                if(isEqual("删除",str)){
                    funcInsert.setName(str + namecn);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());
                    funcInsert.setPermissions(permissionPre + ":single:deleteById" );
                }
                if(isEqual("分页列表",str)){
                    funcInsert.setName( namecn + str);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());

                    funcInsert.setPermissions(permissionPre + ":single:listPage" );
                }
                if(isEqual("懒加载树",str)){
                    funcInsert.setName( namecn + str);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());

                    if(isEqual(map.get("type"),type_tree) ){
                        funcInsert.setPermissions(permissionPre + ":single:getByParentId" );
                        tobeinsertList.add(funcInsert);

                    }
                    continue;
                }
                if(isEqual("不分页列表",str)){
                    funcInsert.setName( namecn + str);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());

                    if(  isEqual("noPageQuery",map.get("noPageQuery"))){
                        funcInsert.setPermissions(permissionPre + ":single:list" );
                        tobeinsertList.add(funcInsert);

                    }
                    continue;
                }
                if(isEqual("启用或禁用",str)){
                    funcInsert.setName( namecn + str);
                    funcInsert.setCode(getPinyin(funcInsert.getName()).getFull());

                    if(isEqual("enable",map.get("enable"))){
                        funcInsert.setPermissions(permissionPre + ":single:enable" );
                        tobeinsertList.add(funcInsert);

                    }
                    continue;
                }

                tobeinsertList.add(funcInsert);
            }

        }

        iFuncService.saveBatch(tobeinsertList);
    }

}