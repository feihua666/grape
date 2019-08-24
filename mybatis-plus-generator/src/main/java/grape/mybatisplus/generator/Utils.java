package grape.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.PackageConfig;

import static grape.mybatisplus.generator.SelfFileOutConfig.path_mvc;

public class Utils {
    /**
     * form 包全名
     * @param packageConfig
     * @return
     */

    public static String controllerFormPackage(PackageConfig packageConfig){

        return controllerFormPath(packageConfig).replace("/",".");
    }

    /**
     * vo 包全名
     * @param packageConfig
     * @return
     */
    public static String controllerVoPackage(PackageConfig packageConfig){

        return controllerVoPath(packageConfig).replace("/",".");
    }
    public static String controllerMapperConverterPackage(PackageConfig packageConfig){

        return controllerMapperConverterPath(packageConfig).replace("/",".");
    }
    public static String controllerVoPath(PackageConfig packageConfig){

        return controllerOtherPath(packageConfig,"/vo");
    }
    public static String controllerMapperConverterPath(PackageConfig packageConfig){

        return controllerOtherPath(packageConfig,"/mapperconverter");
    }
    public static String controllerFormPath(PackageConfig packageConfig){

        return controllerOtherPath(packageConfig,"/form");
    }
    private static String controllerOtherPath(PackageConfig packageConfig,String suffix){

        return packageConfig.getController().replace(".","/").replace(path_mvc,"") + suffix;
    }
}
