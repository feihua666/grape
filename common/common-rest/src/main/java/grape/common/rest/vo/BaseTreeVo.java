package grape.common.rest.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import grape.common.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 树的vo
 * Created by yangwei
 * Created at 2019/7/24 18:25
 */
@Data
public class BaseTreeVo extends BaseIdVo<String> {

    @ApiModelProperty(value = "父级id")
    private String parentId;
    @ApiModelProperty(value = "父级名称")
    private String parentName;
}
