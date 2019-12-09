package grape.base.rest.user.form.identifier;

import grape.base.service.user.po.UserIdentifier;
import grape.common.rest.form.BaseForm;
import grape.common.rest.validation.cross.depend.DependField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户登录帐号表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
// 使用表单验证方式，脚本太多不够优雅
/*@Script.List({
       @Script( script = "getDictCodeById(_this.identityTypeDictId) == 'account_mobile'? mobile(_this.identifier):true",message = "手机号格式不正确",dictIdProp = {"identityTypeDictId"}),
       @Script( script = "getDictCodeById(_this.identityTypeDictId) == 'account_email'? email(_this.identifier):true",message = "邮箱格式不正确",dictIdProp = {"identityTypeDictId"})
})*/
// 最终改用依赖字段验证方式,其实这种方式也挺长的,建议使用属性验证方式grape.common.rest.validation.props.PropValid
@DependField.List({
        @DependField(dependProp = "identityTypeDictId",dict =true, ifEqual = UserIdentifier.type_dict_account_mobile,targetProp = "identifier",patternAlias = "mobile",message = "手机号格式不正确"),
        @DependField(dependProp = "identityTypeDictId",dict =true, ifEqual = UserIdentifier.type_dict_account_email,targetProp = "identifier",patternAlias = "email",message = "邮箱格式不正确")
})
// 表单验证方式
//@Form(dictIdProp = {"identityTypeDictId"})
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="用户创建登录帐号表单")
public class UserIdentifierCreateForm extends BaseForm /*implements IFormValid*/ {

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

/*    @Override
    public void valid(ValidResult result, ValidContext context) {
        result.setReportOn("identifier");
        Map<String,String> dict =  context.getDictIdCode();
        String dictCode = dict.get(this.identityTypeDictId);
        if (!isStrEmpty(this.identifier)) {
            if(isEqual(dictCode, UserIdentifier.TypeDictItem.account_email.name())){
                if (!Validator.isEmail(this.identifier)) {
                    result.setResult(false);
                    result.setErrorMsg("邮箱格式不正确");

                }
            }else if(isEqual(dictCode, UserIdentifier.TypeDictItem.account_mobile.name())){
                if (!Validator.isMobile(this.identifier)) {
                    result.setResult(false);
                    result.setErrorMsg("手机号格式不正确");
                }
            }
        }
    }*/
}
