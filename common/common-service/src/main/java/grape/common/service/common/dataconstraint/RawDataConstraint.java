package grape.common.service.common.dataconstraint;

import grape.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 一个组装原生数据范围约束的实体
 * Created by yangwei
 * Created at 2020/1/15 14:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RawDataConstraint extends BasePojo {

    public RawDataConstraint(){}

    private DefaultDataObject dataObject;
    private List<DefaultDataScope> dataScopes;
}
