package grape.base.rest.user.form;

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
 * 用户启用锁定
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户启用/锁定表单对象")
public class UserEnableForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "启用/锁定状态不能为空")
    @ApiModelProperty(value = "编码",required = true)
    private Boolean isLock;

    @NotEmpty(message = "原因不能为空")
    @ApiModelProperty(value = "启用/锁定原因",required = true)
    private String lockReason;


}
