package grape.base.rest.role.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="RoleListPageForm分页查询条件对象", description="角色表")
public class RoleListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "描述")
    private String remark;


}