package grape.base.rest.user.form.identifier;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户登录帐号分页查询表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户登录帐号分页查询表单")
public class UserIdentifierListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "登录帐号")
    private String identifier;

    @ApiModelProperty(value = "登录帐号类型,字典id")
    private String identityTypeDictId;


}
