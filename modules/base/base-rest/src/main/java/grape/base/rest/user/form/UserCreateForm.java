package grape.base.rest.user.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 添加用户基本信息表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="添加用户基本信息表单")
public class UserCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "登录帐号不能为空")
    @ApiModelProperty(value = "登录帐号",required = true)
    private String account;

    @NotEmpty(message = "帐号密码不能为空")
    @ApiModelProperty(value = "登录帐号密码",required = true)
    private String pwd;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称，姓名",required = true)
    private String nickname;

    @NotEmpty(message = "性别不能为空")
    @ApiModelProperty(value = "性别",notes = "通过字典获取，字典组编码为gender",required = true)
    private String genderDictId;

    @URL(message = "头像必须是一个http开头的绝对路径")
    @ApiModelProperty(value = "头像",notes = "图片绝对路径")
    private String avatar;

    @ApiModelProperty(value = "用户编号",notes = "可以做为员工编号")
    private String serialNo;

    @NotEmpty(message = "部门不能为空")
    @ApiModelProperty(value = "部门id",required = true)
    private String deptId;

    @NotNull(message = "是否虚拟用户不能为空")
    @ApiModelProperty(value = "是否虚拟用户",notes = "虚拟用户代表不是一个真正存在的用户",required = true)
    private Boolean isVirtual;

}
