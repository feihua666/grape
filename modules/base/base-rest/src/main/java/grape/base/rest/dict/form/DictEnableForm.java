package grape.base.rest.dict.form;

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
 * 字典启用禁用
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="启用/禁用表单对象")
public class DictEnableForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "启用/禁用状态不能为空")
    @ApiModelProperty(value = "编码")
    private Boolean disabled;

    @NotEmpty(message = "原因不能为空")
    @ApiModelProperty(value = "启用/禁用原因")
    private String disabledReason;


}
