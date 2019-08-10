package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import grape.common.service.po.NormalBasePo;
import grape.common.service.po.RelBasePo;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/7/22 18:39
 */
@Data
public class SuperGenerator {

    public enum TableType {
        normal,rel,tree
    }

    AutoGenerator mpg = new AutoGenerator();
    private String projectPath;

    private String author;
    // 多个以逗号分隔，最好一个一个的生成
    private String tableNames;
    private String controllerModulePath;
    private String serviceImplModulePath;
    private String serviceApiModulePath;
    private String controllerPakage;
    private String poPakage;
    private String servicePakage;
    private String serviceImplPakage;
    private String mapperPakage;
    // 表类型，normal正常业务实体表，rel单纯关联表，tree树表
    private TableType tableType;


    /**
     * 全局配置
     * @return
     */
    public GlobalConfig globalConfig(){
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setFileOverride(false);
        //  实体属性 Swagger2 注解
        gc.setSwagger2(false);
        gc.setBaseResultMap(true);
        gc.setEntityName("%sPo");
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        gc.setActiveRecord(true);
        return gc;
    }


    /**
     * 数据源配置
     * @return
     */
    public DataSourceConfig dataSourceConfig(){


        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost/grape?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        return dsc;
    }

    /**
     * 包配置
     * @return
     */
    public PackageConfig packageConfig(){
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setEntity(poPakage);
        pc.setService(servicePakage);
        pc.setServiceImpl(serviceImplPakage);
        pc.setController(controllerPakage);
        pc.setXml(mapperPakage);
        pc.setMapper(mapperPakage);
        return pc;
    }

    /**
     * 自定义配置
     * @return
     */
    public InjectionConfig injectionConfig(){
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        return cfg;
    }

    /**
     * 配置模板
     * @return
     */
    public TemplateConfig templateConfig(){
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 不生成xml全部使用单表
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setEntity(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        return templateConfig;
    }
    public StrategyConfig strategyConfig(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);

        String superEntityColumns = "id,is_del,create_by,create_at,update_by,update_at,modified_at,version";
        if(tableType == TableType.normal){
            strategy.setSuperEntityClass(NormalBasePo.class);
        }
        if(tableType == TableType.rel){
            strategy.setSuperEntityClass(RelBasePo.class);
        }
        if(tableType == TableType.tree){
            superEntityColumns += ",level,parent_id";
            for (int i = 1; i <= 10; i++) {
                superEntityColumns += ",parent_id"+i;
            }
            strategy.setSuperEntityClass(TreeBasePo.class);
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("grape.common.rest.mvc.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns.split(","));
        strategy.setInclude(tableNames.split(","));
        // 连字符
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setEntitySerialVersionUID(true);
        strategy.setLogicDeleteFieldName("is_del");
        strategy.setSuperServiceClass("grape.common.service.IBaseService");
        strategy.setSuperServiceImplClass("grape.common.service.BaseServiceImpl");
        strategy.setSuperMapperClass("grape.common.service.IBaseMapper");
        // 乐观锁字段
        strategy.setVersionFieldName("version");

        return strategy;
    }

    /**
     * controller 生成
     * @return
     */
    public FileOutConfig controllerFileOutConfig(){
        return new FileOutConfig("/myTemplates/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + mpg.getPackageInfo().getController().replace(".","/")
                        + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * controller createForm 生成
     * @return
     */
    public FileOutConfig controllerCreateFormFileOutConfig(){
        return new FileOutConfig("/myTemplates/createForm.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + mpg.getPackageInfo().getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "CreateForm" + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * controller updateForm 生成
     * @return
     */
    public FileOutConfig controllerUpdateFormFileOutConfig(){
        return new FileOutConfig("/myTemplates/updateForm.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + mpg.getPackageInfo().getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "UpdateForm" + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * controller listPageForm 生成
     * @return
     */
    public FileOutConfig controllerListPageFormFileOutConfig(){
        return new FileOutConfig("/myTemplates/listPageForm.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + mpg.getPackageInfo().getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "ListPageForm" + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * controller vo 生成
     * @return
     */
    public FileOutConfig controllerVoFileOutConfig(){
        return new FileOutConfig("/myTemplates/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + mpg.getPackageInfo().getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * 实体 生成
     * @return
     */
    public FileOutConfig entityFileOutConfig(){
        return new FileOutConfig("/templates/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + serviceApiModulePath + "/src/main/java/" + mpg.getPackageInfo().getEntity().replace(".","/")
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * mapper接口生成
     * @return
     */
    public FileOutConfig mapperFileOutConfig(){
        return new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + serviceImplModulePath + "/src/main/java/" + mpg.getPackageInfo().getMapper().replace(".","/")
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * service接口生成
     * @return
     */
    public FileOutConfig serviceFileOutConfig(){
        return new FileOutConfig("/templates/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + serviceApiModulePath + "/src/main/java/" + mpg.getPackageInfo().getService().replace(".","/")
                        + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * serviceImpl接口生成
     * @return
     */
    public FileOutConfig serviceImplFileOutConfig(){
        return new FileOutConfig("/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + serviceImplModulePath + "/src/main/java/" + mpg.getPackageInfo().getServiceImpl().replace(".","/")
                        + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        };
    }
    public void doGenerate(){
        // 代码生成器

        mpg.setGlobalConfig(globalConfig());
        mpg.setDataSource(dataSourceConfig());
        mpg.setPackageInfo( packageConfig());
        InjectionConfig cfg =  injectionConfig();
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义controller输出
        if(!StringUtils.isEmpty(controllerModulePath)){
            focList.add(controllerFileOutConfig());
            focList.add(controllerCreateFormFileOutConfig());
            focList.add(controllerUpdateFormFileOutConfig());
            focList.add(controllerListPageFormFileOutConfig());
            focList.add(controllerVoFileOutConfig());
        }
        // 自定义实体输出
        if(!StringUtils.isEmpty(serviceApiModulePath)){
            focList.add(entityFileOutConfig());
        }
        // 自定义mapper输出
        if(!StringUtils.isEmpty(serviceImplModulePath)){
            focList.add(mapperFileOutConfig());
        }
        // 自定义service输出
        if(!StringUtils.isEmpty(serviceApiModulePath)){
            focList.add(serviceFileOutConfig());
        }
        // 自定义serviceImpl输出
        if(!StringUtils.isEmpty(serviceImplModulePath)){
            focList.add(serviceImplFileOutConfig());
        }
        // todo
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(templateConfig());
        mpg.setStrategy(strategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
