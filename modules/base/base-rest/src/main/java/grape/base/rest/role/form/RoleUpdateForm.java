package grape.base.rest.role.form;

import grape.common.rest.form.BaseUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 角色更新表单对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="角色更新表单对象")
public class RoleUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "角色名称",required = true)
    private String name;

    @ApiModelProperty(value = "父id",notes = "如果该节点下还有子节点，不允许修改该值，也就是说只允许移动叶子节点")
    private String parentId;

    @ApiModelProperty(value = "描述")
    private String remark;


}
