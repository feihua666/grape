package grape.base.rest.dept.form;
import grape.common.rest.form.BaseForm;

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
 * 部门更新表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="部门更新表单对象")
public class DeptUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "部门名称")
    private String name;

    @NotEmpty(message = "类型不能为空")
    @ApiModelProperty(value = "类型,字典id",notes = "字典组编码 ")
    private String typeDictId;

    @ApiModelProperty(value = "负责人用户id，该id可用来填充审批人")
    private String masterUserId;

    @NotNull(message = "是否虚拟部门不能为空")
    @ApiModelProperty(value = "是否虚拟部门")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述")
    private String remark;



}
