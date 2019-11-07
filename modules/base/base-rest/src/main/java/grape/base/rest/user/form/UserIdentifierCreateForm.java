package grape.base.rest.user.form;
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
 * 用户登录标识表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户登录标识表单")
public class UserIdentifierCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    @NotEmpty(message = "登录标识不能为空")
    @ApiModelProperty(value = "登录标识",required = true)
    private String identifier;

    @NotEmpty(message = "登录类型不能为空")
    @ApiModelProperty(value = "登录标识,字典id",notes = "字典选项",required = true)
    private String identityTypeDictId;

}
