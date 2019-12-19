package grape.base.service.dataconstraint.dto;

import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.common.service.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/12/18 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataObjectAndScopeDto extends BaseDto {

    /**
     * 数据对象
     */
    private DataObject dataObject;
    /**
     * 数据对象下的数据范围
     */
    private List<DataScope> dataScopes;
}
