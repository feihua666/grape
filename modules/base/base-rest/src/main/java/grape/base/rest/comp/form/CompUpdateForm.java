package grape.base.rest.comp.form;
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
 * 公司更新表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="公司更新表单对象")
public class CompUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "公司名称不能为空")
    @ApiModelProperty(value = "公司名称",required = true)
    private String name;

    @ApiModelProperty(value = "负责人用户id",notes = "该id可用来填充审批人")
    private String masterUserId;

    @ApiModelProperty(value = "是否虚拟公司")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述、备注")
    private String remark;


}
