package grape.common.rest.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 树的vo
 * Created by yangwei
 * Created at 2019/7/24 18:25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class BaseTreeVo extends BaseIdVo<String> {

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "父级名称")
    private String parentName;
}
