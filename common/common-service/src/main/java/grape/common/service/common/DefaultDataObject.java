package grape.common.service.common;

import grape.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 默认的数据对象
 * Created by yangwei
 * Created at 2019/12/3 15:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class DefaultDataObject extends BasePojo implements IDataObject<DefaultDataObject> {
    private String code;

    @Override
    public String dataObjectCode() {
        return code;
    }
}
