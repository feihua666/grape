package grape.base.rest.user.form.identifier;
import grape.common.rest.form.BaseForm;

import grape.common.rest.validation.Script;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户登录帐号表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Script.List({
       @Script( script = "getDictCodeById(_this.identityTypeDictId) == 'account_mobile'? mobile(_this.identifier):true",message = "手机号格式不正确",dictIdProp = {"identityTypeDictId"}),
       @Script( script = "getDictCodeById(_this.identityTypeDictId) == 'account_email'? email(_this.identifier):true",message = "邮箱格式不正确",dictIdProp = {"identityTypeDictId"})
})
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="用户登录帐号表单")
public class UserIdentifierCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    @NotEmpty(message = "登录帐号不能为空")
    @ApiModelProperty(value = "登录帐号",required = true)
    private String identifier;

    @NotEmpty(message = "登录类型不能为空")
    @ApiModelProperty(value = "登录帐号,字典id",notes = "字典选项",required = true)
    private String identityTypeDictId;

}
