package grape.base.rest.userrolerel.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单功能关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="UserRoleRelVo数据响应对象", description="角色菜单功能关系表，多对多")
public class UserRoleRelVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    private String roleId;


}
