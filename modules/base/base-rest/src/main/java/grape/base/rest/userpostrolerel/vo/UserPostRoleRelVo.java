package grape.base.rest.userpostrolerel.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位与角色关系表,决定了用户的功能
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="UserPostRoleRelVo数据响应对象", description="用户岗位与角色关系表,决定了用户的功能")
public class UserPostRoleRelVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户岗位表id")
    private String userPostId;

    @ApiModelProperty(value = "角色id")
    private String roleId;


}
