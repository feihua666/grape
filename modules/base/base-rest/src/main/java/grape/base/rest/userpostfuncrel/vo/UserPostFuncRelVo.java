package grape.base.rest.userpostfuncrel.vo;
import grape.common.rest.vo.BaseIdVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位功能关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户岗位功能关系表，多对多数据响应对象")
public class UserPostFuncRelVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户岗位id")
    private String userPostId;

    @ApiModelProperty(value = "功能id")
    private String funcId;


}
