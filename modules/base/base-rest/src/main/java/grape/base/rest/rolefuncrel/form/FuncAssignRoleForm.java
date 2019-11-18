package grape.base.rest.rolefuncrel.form;

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
 * 功能分配角色
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="功能分配角色表单对象")
public class FuncAssignRoleForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "功能id不能为空")
    @ApiModelProperty(value = "功能id")
    private String funcId;

    @ApiModelProperty(value = "选择的角色id")
    private List<String> checkedRoleIds;

    @ApiModelProperty(value = "未选择的角色id",notes = "如果为懒加载请传已加载数据的未选择角色")
    private List<String> uncheckedRoleIds;

    @NotNull(message = "是否为懒加载不能为空")
    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad;
}
