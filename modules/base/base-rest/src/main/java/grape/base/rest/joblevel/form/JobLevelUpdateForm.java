package grape.base.rest.joblevel.form;
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
 * 职务级别表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="职务级别更新表单对象")
public class JobLevelUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="职务级别名称不能为空")
    @ApiModelProperty(value = "职务级别名称")
    private String name;

    @NotEmpty(message="描述不能为空")
    @ApiModelProperty(value = "描述")
    private String remark;



}
