package grape.common.service.common.dataconstraint;

import java.util.List;

/**
 * 数据范围接口
 * Created by yangwei
 * Created at 2020/1/15 13:59
 */
public interface IDataScope {

    String id();
    String dataScopeCode();
    String constraintContent();

    boolean isCustom();

    List<String> customDataIds();
}
