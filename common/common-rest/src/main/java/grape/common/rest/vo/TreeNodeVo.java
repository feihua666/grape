package grape.common.rest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 展示树节点
 * Created by yangwei
 * Created at 2019/9/24 16:55
 */
@Data
@ApiModel(value="展示树节点")
public class TreeNodeVo<T> extends BaseVo {
    @ApiModelProperty(value = "当前节点")
    private T node;
    @ApiModelProperty(value = "子节点")
    private List<T> children;
}
