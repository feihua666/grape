package grape.base.rest.user.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="修改密码表单")
public class UserPwdUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;


    @NotNull(message = "原密码不能为空")
    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    @NotNull(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码")
    private String newPassword;


}
