package grape.common.rest.vo;

import grape.common.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 树的vo
 * Created by yangwei
 * Created at 2019/7/24 18:25
 */
@Data
public class BaseTreeVo extends BaseVo {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long parentId;
}
