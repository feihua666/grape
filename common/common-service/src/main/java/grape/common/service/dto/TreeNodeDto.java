package grape.common.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 树节点
 * Created by yangwei
 * Created at 2019/9/24 16:53
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class TreeNodeDto<T> extends BaseDto {
    private T node;
    private List<TreeNodeDto<T>> children;
}
