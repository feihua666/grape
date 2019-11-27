package grape.base.rest.userpostrolerel.form;

import grape.common.rest.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户岗位分配角色
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="用户岗位分配角色表单对象")
public class UserPostAssignRoleForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户岗位id不能为空")
    @ApiModelProperty(value = "用户岗位id")
    private String userPostId;

    @NotEmpty(message = "角色id不能为空")
    @ApiModelProperty(value = "选择的角色id")
    private String checkedRoleId;

}
