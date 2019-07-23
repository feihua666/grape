package grape.mybatisplus.generator;

/**
 * base 模块生成
 * Created by yangwei
 * Created at 2019/7/22 18:40
 */
public class BaseGenerator extends SuperGenerator {
    public static void main(String[] args) {

        BaseGenerator generator = new BaseGenerator();
        generator.setAuthor("yangwei");
        generator.setTableNames("base_user");

        generator.setControllerModulePath("/base/base-rest");
        generator.setServiceApiModulePath("/base/base-service/base-service-api");
        generator.setServiceImplModulePath("/base/base-service/base-service-impl");


        generator.setControllerPakage("grape.base.rest.mvc");
        generator.setServicePakage("grape.base.service.api.service");
        generator.setServiceImplPakage("grape.base.service.impl.impl");
        generator.setPoPakage("grape.base.service.api.po");
        generator.setMapperPakage("grape.base.service.impl.mapper");

        generator.doGenerate();
    }
}
