package grape.base.rest.userrolerel.form;

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
 * 角色分配用户
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="角色分配用户表单对象")
public class RoleAssignUserForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "选择的用户id")
    private List<String> checkedUserIds;

    @ApiModelProperty(value = "未选择的用户id",notes = "如果为懒加载请传该值")
    private List<String> uncheckedUserIds;

    @NotNull(message = "是否为懒加载不能为空")
    @ApiModelProperty(value = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad;
}
