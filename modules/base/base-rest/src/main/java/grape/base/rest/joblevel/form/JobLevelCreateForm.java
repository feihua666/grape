package grape.base.rest.joblevel.form;
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
 * 职务级别表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="职务级别添加表单对象")
public class JobLevelCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="职务级别编码不能为空")
    @ApiModelProperty(value = "职务级别编码",required = true)
    private String code;
    
    @NotEmpty(message="职务级别名称不能为空")
    @ApiModelProperty(value = "职务级别名称",required = true)
    private String name;
    
    @NotEmpty(message="职务id不能为空")
    @ApiModelProperty(value = "职务ID",required = true)
    private String jobId;

    @ApiModelProperty(value = "描述")
    private String remark;
    


}
