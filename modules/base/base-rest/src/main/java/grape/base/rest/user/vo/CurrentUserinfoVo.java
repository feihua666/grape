package grape.base.rest.user.vo;

import grape.base.rest.application.vo.ApplicationVo;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.role.vo.RoleVo;
import grape.common.rest.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/9/27 15:56
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="当前登录用户信息")
public class CurrentUserinfoVo extends BaseVo {

    @ApiModelProperty(value = "当前登录用户id")
    private String id;
    @ApiModelProperty(value = "当前登录昵称")
    private String nickname;
    @ApiModelProperty(value = "头像地址，绝对地址")
    private String avatar;
    @ApiModelProperty(value = "是否切换角色，true=切换角色，false=切换岗位")
    private Boolean isSwitchRole;

    @ApiModelProperty(value = "所属公司id")
    private String compId;
    @ApiModelProperty(value = "所属公司名称")
    private String  compName;

    @ApiModelProperty(value = "所属部门id")
    private String deptId;
    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "当前使用的角色id")
    private String currentRoleId;
    @ApiModelProperty(value = "当前使用的角色编码")
    private String currentRoleCode;
    @ApiModelProperty(value = "当前使用的角色名称")
    private String currentRoleName;

    @ApiModelProperty(value = "可切换的角色们")
    private List<RoleVo> roles;

    @ApiModelProperty(value = "当前使用的岗位id,如果是切换角色，该值为空")
    private String currentPostId;
    @ApiModelProperty(value = "当前使用的岗位编码,如果是切换角色，该值为空")
    private String currentPostCode;
    @ApiModelProperty(value = "当前使用的岗位名称,如果是切换角色，该值为空")
    private String currentPostName;

    @ApiModelProperty(value = "可切换的岗位们")
    private List<PostVo> posts;

    @ApiModelProperty(value = "可用的应用们")
     private List<ApplicationVo> applications;

}