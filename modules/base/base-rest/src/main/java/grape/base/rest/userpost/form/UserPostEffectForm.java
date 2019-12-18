package grape.base.rest.userpost.form;

import grape.common.rest.form.BaseUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户岗位生效失效
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户岗位生效/失效表单对象")
public class UserPostEffectForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "生效/失效状态不能为空")
    @ApiModelProperty(value = "生效/锁定状态",required = true)
    private Boolean isEffect;

    @NotEmpty(message = "原因不能为空")
    @ApiModelProperty(value = "生效/失效原因",required = true)
    private String ineffectReason;


}
