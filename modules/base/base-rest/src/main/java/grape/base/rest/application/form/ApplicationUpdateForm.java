package grape.base.rest.application.form;
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
 * 应用表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="应用更新表单对象")
public class ApplicationUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;


    @NotEmpty(message="应用名称不能为空")
    @ApiModelProperty(value = "应用名称",required = true)
    private String name;

    @ApiModelProperty(value = "描述")
    private String remark;



}
