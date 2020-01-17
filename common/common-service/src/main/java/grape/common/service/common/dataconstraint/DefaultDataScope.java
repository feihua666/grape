package grape.common.service.common.dataconstraint;

import grape.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 默认的数据范围对象
 * Created by yangwei
 * Created at 2019/12/3 15:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultDataScope extends BasePojo implements IDataScope {


    private String id;
    private String dataScopeCode;
    private String constraintContent;
    private boolean isCustom;
    private List<String> customDataIds;

    @Override
    public String id() {
        return id;
    }

    @Override
    public String dataScopeCode() {
        return dataScopeCode;
    }

    @Override
    public String constraintContent() {
        return constraintContent;
    }

    @Override
    public boolean isCustom() {
        return isCustom;
    }

    @Override
    public List<String> customDataIds() {
        return customDataIds;
    }
}
