package grape.base.rest.user.vo;

import grape.common.rest.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by yangwei
 * Created at 2019/9/27 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="当前登录用户信息")
public class CurrentUserinfoVo extends BaseVo {

    @ApiModelProperty(value = "当前登录用户id")
    private Long id;
    @ApiModelProperty(value = "当前登录昵称")
    private String nickname;
    @ApiModelProperty(value = "头像地址，绝对地址")
    private String avatar;
    @ApiModelProperty(value = "是否切换角色，true=切换角色，false=切换岗位")
    private Boolean isSwitchRole;

    @ApiModelProperty(value = "所属公司id")
    private Long compId;
    @ApiModelProperty(value = "所属公司名称")
    private String  compName;

    @ApiModelProperty(value = "所属部门id")
    private Long deptId;
    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "当前使用的角色id")
    private Long roleId;
    @ApiModelProperty(value = "当前使用的岗位id,如果是切换角色，该值为空")
    private Long postId;

}
