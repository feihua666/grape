package grape.mybatisplus.modules;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import grape.mybatisplus.generator.SuperGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static grape.mybatisplus.generator.SelfFileOutConfig.dot_mvc;

/**
 * base 模块生成
 * Created by yangwei
 * Created at 2019/7/22 18:40
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseGenerator extends SuperGenerator {

    private static String parentPakage="grape";
    private static String author="author";
    private static String moduleName="moduleName";
    private static String tableName="tableName";
    private static String tableType="tableType";
    private static String tablePrefix="tablePrefix";

    public static void main(String[] args) {



        String parentModule = "/modules/base";
        String restModeule = "/base-rest";
        String serviceModeule = "/base-service";
        String serviceApiModule = "/base-service-api";

        for (Map<String, String> stringStringMap : tableConfig()) {
            BaseGenerator generator = new BaseGenerator();

            generator.setAuthor(stringStringMap.get(author));

            generator.setTableName(stringStringMap.get(tableName));
            generator.setModuleName(stringStringMap.get(moduleName));
            generator.setTableType(TableType.valueOf(stringStringMap.get(tableType)));
            //取表名第一组作为前缀
            generator.setTablePrefix(stringStringMap.get(tablePrefix));

            String serviceImplModule = serviceApiModule.replace("-api","-impl");


            generator.setControllerModulePath(parentModule + restModeule);
            generator.setServiceApiModulePath(parentModule + serviceModeule + serviceApiModule);
            generator.setServiceImplModulePath(parentModule + serviceModeule + serviceImplModule);

            String serviceApiParentPakage = parentPakage + replace(serviceModeule);
            String serviceImplParentPakage = serviceApiParentPakage;


            generator.setControllerPakage(parentPakage + replace(restModeule) + "." + generator.getModuleName() + dot_mvc);
            generator.setServicePakage(serviceApiParentPakage + "." + generator.getModuleName() + ".api");
            generator.setServiceImplPakage(serviceImplParentPakage + "." + generator.getModuleName() + ".impl");
            generator.setPoPakage(serviceApiParentPakage + "." + generator.getModuleName() + ".po");
            generator.setMapperPakage(serviceImplParentPakage + "." + generator.getModuleName() + ".mapper");

            generator.doGenerate();
        }


    }
    static List<Map<String,String>> tableConfig = new ArrayList<>();
    public static List<Map<String,String>> tableConfig(){
        String author = "yangwei";
        String tablePrefix = "base";

        // 区域表
        commonSet("base_area","area",TableType.tree,tablePrefix,author);
        // 用户表
        commonSet("base_user","user",TableType.normal,tablePrefix,author);
        // 用户登录标识表
        commonSet("base_user_identifier","user",TableType.normal,tablePrefix,author);
        // 用户密码表
        commonSet("base_user_pwd","user",TableType.normal,tablePrefix,author);
        // 字典表
        commonSet("base_dict","dict",TableType.tree,tablePrefix,author);
        // 功能表
        commonSet("base_func","func",TableType.tree,tablePrefix,author);
        // 功能表
        commonSet("base_role","role",TableType.tree,tablePrefix,author);
        commonSet("base_role_func_rel","rolefuncrel",TableType.rel,tablePrefix,author);
        commonSet("base_user_post_role_rel","userpostrolerel",TableType.rel,tablePrefix,author);
        commonSet("base_comp","comp",TableType.tree,tablePrefix,author);
        commonSet("base_dept","dept",TableType.tree,tablePrefix,author);
        commonSet("base_post","post",TableType.normal,tablePrefix,author);
        commonSet("base_user_post","userpost",TableType.normal,tablePrefix,author);
        commonSet("base_user_role_rel","userrolerel",TableType.rel,tablePrefix,author);
        commonSet("base_job","job",TableType.normal,tablePrefix,author);
        commonSet("base_job_level","joblevel",TableType.normal,tablePrefix,author);

        return  tableConfig;
    }

    private static void commonSet(String tableName,String moduleName,TableType tableType,String tablePrefix,String author){
        Map<String, String> tableConfigItem;
        tableConfigItem = new HashMap<>();
        tableConfigItem.put(BaseGenerator.tableName,tableName);
        tableConfigItem.put(BaseGenerator.moduleName,moduleName);
        tableConfigItem.put(BaseGenerator.tableType,tableType.name());
        tableConfigItem.put(BaseGenerator.tablePrefix,tablePrefix);
        tableConfigItem.put(BaseGenerator.author,author);
        tableConfig.add(tableConfigItem);
    }
    /**
     * 数据源配置
     * @return
     */
    public DataSourceConfig dataSourceConfig(){


        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://39.104.146.45/grape?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("jlks82lsUH@@");
        return dsc;
    }
    private static String replace(String str){
        return str.replace("/",".").replace("-",".");
    }

}
