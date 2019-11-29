package grape.base.rest.user.form.identifier;
import grape.base.service.user.po.UserIdentifier;
import grape.common.rest.form.BaseForm;

import grape.common.rest.validation.Script;
import grape.common.rest.validation.cross.depend.DependField;
import grape.common.rest.validation.cross.depend.DependFieldValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户登录帐号表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */

@DependField.List({
        @DependField(dependProp = "identityTypeDictId",dict =true, equal = UserIdentifier.type_dict_account_mobile,targetProp = "identifier",patternAlias = DependFieldValidator.pattern_alias_mobile,message = "手机号格式不正确"),
        @DependField(dependProp = "identityTypeDictId",dict =true, equal = UserIdentifier.type_dict_account_email,targetProp = "identifier",patternAlias = DependFieldValidator.pattern_alias_email,message = "邮箱格式不正确")
})
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="UserIdentifierUpdateForm更新表单对象", description="用户登录帐号表")
public class UserIdentifierUpdateForm extends BaseForm {

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
