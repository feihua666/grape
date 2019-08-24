package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

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
public class BaseGenerator extends SuperGenerator {

    private static String parentPakage="grape";
    private static String author="author";
    private static String moduleName="moduleName";
    private static String tableName="tableName";
    private static String tableType="tableType";
    private static String tablePrefix="tablePrefix";

    public static void main(String[] args) {

        BaseGenerator generator = new BaseGenerator();

        String parentModule = "/modules/base";
        String restModeule = "/base-rest";
        String serviceModeule = "/base-service";
        String serviceApiModule = "/base-service-api";

        for (Map<String, String> stringStringMap : generator.tableConfig()) {


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
    public List<Map<String,String>> tableConfig(){
        String author = "yangwei";
        String tablePrefix = "base";
        Map<String, String> tableConfigItem;
        List<Map<String,String>> tableConfig = new ArrayList<>();

        tableConfigItem = new HashMap<>();
        tableConfigItem.put(tableName,"base_area");
        tableConfigItem.put(moduleName,"area");
        tableConfigItem.put(tableType,TableType.tree.name());
        tableConfigItem.put(BaseGenerator.tablePrefix,tablePrefix);
        tableConfigItem.put(BaseGenerator.author,author);
        tableConfig.add(tableConfigItem);

        return  tableConfig;
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
