package grape.base.rest.org.form;
import grape.common.rest.form.BaseUpdateForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * <p>
 * 组织树表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@ScriptAssert.List({
        @ScriptAssert(message = "只能关联一个公司或部门" ,lang = "javascript",script = "(!!_this.relationCompId) && (!!_this.relationDeptId)")
}
)
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="组织树更新表单对象")
public class OrgUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "父id",notes = "如果该节点下还有子节点，不允许修改该值，也就是说只允许移动叶子节点")
    private String parentId;

    @NotEmpty(message="组织树名称id不能为空")
    @ApiModelProperty(value = "组织树名称id",required = true)
    private String orgNameId;

    @ApiModelProperty(value = "关联公司id")
    private String relationCompId;

    @ApiModelProperty(value = "关联部门id")
    private String relationDeptId;

    @NotEmpty(message="组织树类型不能为空")
    @ApiModelProperty(value = "类型，字典",required = true,notes = "字典组编码 org_type")
    private String typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;



}
