package grape.common.rest.vo;

import grape.common.service.trans.TransField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 展示树节点
 * Created by yangwei
 * Created at 2019/9/24 16:55
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@ApiModel(value="展示树节点")
public class TreeNodeVo<T extends BaseVo> extends BaseIdVo<String> {


    public TreeNodeVo(){}

    @TransField
    @ApiModelProperty(value = "父节点")
    private T node;
    @ApiModelProperty(value = "子节点")
    @TransField
    private List<TreeNodeVo<T>> children;
    @ApiModelProperty(value = "是否有子节点",notes = "该属性可直接用来判断是为叶子节点")
    private boolean hasChildren;
}
