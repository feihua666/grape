package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import grape.common.service.po.NormalBasePo;
import grape.common.service.po.RelBasePo;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    // 一次只能生成一个表
    private String tableName;
    // 模块名称，一个表对应一个模块，方便查找
    private String moduleName;
    // 表前缀
    private String tablePrefix;

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
        //gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setFileOverride(false);
        //  实体属性 Swagger2 注解
        gc.setSwagger2(false);
        gc.setBaseResultMap(false);
        // 实体添加持久化后缀标识
        //gc.setEntityName("%sPo");
        gc.setOpen(false);
        gc.setBaseColumnList(true);
        // 说白了就是entity继承一个model带数据的操作
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
        //pc.setModuleName(moduleName);
        pc.setEntity(poPakage);
        pc.setService(servicePakage);
        pc.setServiceImpl(serviceImplPakage);
        pc.setController(controllerPakage);
        pc.setXml(mapperPakage);
        pc.setMapper(mapperPakage);

        return pc;
    };

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
                Map<String,Object> map = new HashMap<>();
                map.put("formPackage",Utils.controllerFormPackage(mpg.getPackageInfo()));
                map.put("voPackage",Utils.controllerVoPackage(mpg.getPackageInfo()));
                map.put("mapperWebPackage",Utils.controllerMapperConverterPackage(mpg.getPackageInfo()));
                map.put("treeTable",tableType == TableType.tree);
                this.setMap(map);
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
        templateConfig.setEntity(null); // 默认模板
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        return templateConfig;
    }
    public StrategyConfig strategyConfig(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 公共父类
        String superEntityColumns = "id,is_del,create_by,create_at,update_by,update_at,version";
        if(tableType == TableType.normal){
            strategy.setSuperEntityClass(NormalBasePo.class);
        }
        if(tableType == TableType.rel){
            strategy.setSuperEntityClass(RelBasePo.class);
        }
        if(tableType == TableType.tree){
            superEntityColumns += ",level,parent_id";
            for (int i = 1; i <= 10; i++) {
                superEntityColumns += ",parent_id" + i;
            }
            strategy.setSuperEntityClass(TreeBasePo.class);
        }

        strategy.setSuperControllerClass("grape.common.rest.mvc.BaseController");
        if(tableType == TableType.tree){
            strategy.setSuperServiceClass("grape.common.service.IBaseTreeService");
        }else {
            strategy.setSuperServiceClass("grape.common.service.IBaseService");
        }
        strategy.setSuperServiceImplClass("grape.common.service.BaseServiceImpl");
        strategy.setSuperMapperClass("grape.common.service.IBaseMapper");

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns.split(","));
        strategy.setInclude(tableName);
        // 连字符
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        strategy.setEntitySerialVersionUID(true);
        strategy.setTablePrefix(tablePrefix);
        //strategy.setLogicDeleteFieldName("is_del");

        //strategy. setTableFillList()
        // 乐观锁字段
        strategy.setVersionFieldName("version");

        return strategy;
    }


    public void doGenerate(){
        // 代码生成器

        mpg.setGlobalConfig(globalConfig());
        mpg.setDataSource(dataSourceConfig());
        mpg.setPackageInfo( packageConfig());
        InjectionConfig cfg =  injectionConfig();

        // todo
        SelfFileOutConfig selfFileOutConfig = new SelfFileOutConfig(projectPath,controllerModulePath,serviceImplModulePath,serviceApiModulePath,mpg.getPackageInfo());
        List<FileOutConfig> fileOutConfigList = selfFileOutConfig.getFileOutConfigList();
        if (!fileOutConfigList.isEmpty()) {
            cfg.setFileOutConfigList(fileOutConfigList);
        }
        mpg.setCfg(cfg);
        mpg.setTemplate(templateConfig());
        mpg.setStrategy(strategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
