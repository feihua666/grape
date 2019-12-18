package grape.base.rest.user.vo;

import grape.base.rest.application.vo.ApplicationVo;
import grape.base.rest.func.vo.FuncVo;
import grape.base.rest.post.vo.PostVo;
import grape.base.rest.role.vo.RoleVo;
import grape.base.rest.userpost.vo.UserPostVo;
import grape.common.rest.vo.BaseVo;
import grape.common.service.trans.TransField;
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

    @ApiModelProperty(value = "超级管理员")
    private Boolean isSuperAdmin;

    @ApiModelProperty(value = "所属公司id")
    private String compId;
    @ApiModelProperty(value = "所属公司名称")
    private String  compName;

    @ApiModelProperty(value = "所属部门id")
    private String deptId;
    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @TransField
    @ApiModelProperty(value = "直接分配的角色")
    private List<RoleVo> roles;


    @TransField
    @ApiModelProperty(value = "分配的岗位")
    private List<UserPostVo> userPosts;

    @TransField
    @ApiModelProperty(value = "可用的应用们")
    private List<ApplicationVo> applications;

    @TransField
    @ApiModelProperty(value = "可用功能")
    private List<FuncVo> funcs;

}