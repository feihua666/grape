package grape.common.service.common;

import grape.common.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据约束内容对象
 * Created by yangwei
 * Created at 2019/12/3 14:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ConstraintContent extends BasePojo {

    private String content;
}
