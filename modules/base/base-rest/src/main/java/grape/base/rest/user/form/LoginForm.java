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
 * 后台登录表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="后台管理登录")
public class LoginForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "帐号不能为空")
    @ApiModelProperty(value = "帐号、邮箱、手机号等",required = true)
    private String username;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
