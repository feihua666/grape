package grape.base.rest.job.form;
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
 * 职务表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="JobCreateForm添加表单对象", description="职务表")
public class JobCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "职务编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "职务名称",required = true)
    private String name;

    @NotNull(message = "是否公共不能为空")
    @ApiModelProperty(value = "是否公共",required = true)
    private Boolean isPublic;

    @ApiModelProperty(value = "类型，字典id",notes = "字典组编码job_type")
    private String typeDictId;

    @ApiModelProperty(value = "部门id",notes = "是否公共为否时必填")
    private String deptId;

    @ApiModelProperty(value = "描述")
    private String remark;


}
