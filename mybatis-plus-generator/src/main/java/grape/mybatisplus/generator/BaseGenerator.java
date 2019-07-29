package grape.mybatisplus.generator;

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
        generator.setTableNames("base_user");
        generator.setTableType(TableType.normal);

        String parentModule = "/modules/base";
        String restModeule = "/base-rest";
        String serviceModeule = "/base-service";
        String serviceApiModule = "/base-service-api";


        String serviceImplModule = serviceApiModule.replace("-api","-impl");

        generator.setControllerModulePath(parentModule + restModeule);
        generator.setServiceApiModulePath(parentModule + serviceModeule + serviceApiModule);
        generator.setServiceImplModulePath(parentModule + serviceModeule + serviceImplModule);

        String serviceApiParentPakage = parentPakage + serviceApiModule.replace("/",".").replace("-",".");
        String serviceImplParentPakage = parentPakage + serviceImplModule.replace("/",".").replace("-",".");
        generator.setControllerPakage(parentPakage + restModeule.replace("/",".").replace("-",".") + ".mvc");
        generator.setServicePakage(serviceApiParentPakage + ".service");
        generator.setServiceImplPakage(serviceImplParentPakage +".impl");
        generator.setPoPakage(serviceApiParentPakage + ".po");
        generator.setMapperPakage(serviceImplParentPakage + ".mapper");

        generator.doGenerate();
    }
}
