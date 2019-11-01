package grape.base.rest.role.form;
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
 * 角色添加表单对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="角色添加表单对象")
public class RoleCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父级id")
    private String parentId;

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "角色编码")
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String remark;


}
