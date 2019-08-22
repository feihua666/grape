package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/8/22 9:08
 */
@Data
@AllArgsConstructor
public class SelfFileOutConfig {

    private String projectPath;

    private String controllerModulePath;
    private String serviceImplModulePath;
    private String serviceApiModulePath;
    private PackageConfig packageConfig;


    public List<FileOutConfig> getFileOutConfigList(){
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义controller输出
        if(!StringUtils.isEmpty(controllerModulePath)){
            focList.add(controllerFileOutConfig());
            focList.add(controllerCreateFormFileOutConfig());
            focList.add(controllerUpdateFormFileOutConfig());
            focList.add(controllerListPageFormFileOutConfig());
            focList.add(controllerVoFileOutConfig());
            focList.add(controllerMapperConverterFileOutConfig());
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
        return focList;
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
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
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
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
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
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
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
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
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
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        };
    }
    /**
     * controller mapper converter 生成
     * @return
     */
    public FileOutConfig controllerMapperConverterFileOutConfig(){
        return new FileOutConfig("/myTemplates/controllerMapperConverter.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + controllerModulePath + "/src/main/java/" + packageConfig.getController().replace(".","/")
                        + "/" + tableInfo.getEntityName() + "ControllerMapper" + StringPool.DOT_JAVA;
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
                return projectPath + serviceApiModulePath + "/src/main/java/" + packageConfig.getEntity().replace(".","/")
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
                return projectPath + serviceImplModulePath + "/src/main/java/" + packageConfig.getMapper().replace(".","/")
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
                return projectPath + serviceApiModulePath + "/src/main/java/" + packageConfig.getService().replace(".","/")
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
                return projectPath + serviceImplModulePath + "/src/main/java/" + packageConfig.getServiceImpl().replace(".","/")
                        + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        };
    }
}
