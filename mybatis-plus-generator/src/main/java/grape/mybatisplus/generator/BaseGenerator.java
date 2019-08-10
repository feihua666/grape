package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * base 模块生成
 * Created by yangwei
 * Created at 2019/7/22 18:40
 */
public class BaseGenerator extends SuperGenerator {

    private static String parentPakage="grape";

    public static void main(String[] args) {

        BaseGenerator generator = new BaseGenerator();
        generator.setAuthor("yangwei");
        //generator.setTableNames("base_user");
        generator.setTableNames("base_user,base_user_auth");
        generator.setTableType(TableType.normal);

        String parentModule = "/modules/base";
        String restModeule = "/base-rest";
        String serviceModeule = "/base-service";
        String serviceApiModule = "/base-service-api";


        String serviceImplModule = serviceApiModule.replace("-api","-impl");

        generator.setControllerModulePath(parentModule + restModeule);
        generator.setServiceApiModulePath(parentModule + serviceModeule + serviceApiModule);
        generator.setServiceImplModulePath(parentModule + serviceModeule + serviceImplModule);

        String serviceApiParentPakage = parentPakage + replace(serviceApiModule);
        String serviceImplParentPakage = parentPakage + replace(serviceImplModule);
        generator.setControllerPakage(parentPakage + replace(restModeule) + ".mvc");
        generator.setServicePakage(serviceApiParentPakage + ".service");
        generator.setServiceImplPakage(serviceImplParentPakage +".impl");
        generator.setPoPakage(serviceApiParentPakage + ".po");
        generator.setMapperPakage(serviceImplParentPakage + ".mapper");

        generator.doGenerate();
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
