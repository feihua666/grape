package grape.base.rest.comp.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 公司添加表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="公司添加表单")
public class CompCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级id",notes = "添加根节点不传值")
    private String parentId;

    @NotNull(message = "公司编码不能为空")
    @ApiModelProperty(value = "公司编码")
    private String code;

    @NotNull(message = "公司名称不能为空")
    @ApiModelProperty(value = "公司名称")
    private String name;

    @NotNull(message = "公司类型不能为空")
    @ApiModelProperty(value = "类型,字典id",notes = "字典组编码comp_type")
    private String typeDictId;

    @ApiModelProperty(value = "负责人用户id，该id可用来填充审批人")
    private String masterUserId;

    @ApiModelProperty(value = "是否虚拟公司")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述、备注")
    private String remark;


}
