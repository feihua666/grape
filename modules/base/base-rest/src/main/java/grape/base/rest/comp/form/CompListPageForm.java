package grape.base.rest.comp.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CompListPageForm分页查询条件对象", description="部门表")
public class CompListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司编码")
    private String code;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "类型,字典id")
    private Long typeDictId;

    @ApiModelProperty(value = "负责人用户id，该id可用来填充审批人")
    private Long masterUserId;

    @ApiModelProperty(value = "是否虚拟公司")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述、备注")
    private String remark;


}
