package grape.base.rest.user.form.pwd;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户密码修改
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="修改密码表单")
public class UserPwdUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "原密码不能为空")
    @ApiModelProperty(value = "原密码",required = true)
    private String oldPassword;

    @NotEmpty(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;


}
