package grape.base.rest.paramconfig.form;
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
 * 参数配置表
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="参数配置添加表单对象")
public class ParamConfigCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="昵称，姓名不能为空")
    @ApiModelProperty(value = "昵称，姓名",required = true)
    private String code;

    @NotEmpty(message="参数配置名称不能为空")
    @ApiModelProperty(value = "参数配置名称",required = true)
    private String name;

    @NotEmpty(message="参数配置值不能为空")
    @ApiModelProperty(value = "参数配置值",required = true)
    private String value;

    @NotEmpty(message="配置值的类型，字典组id不能为空")
    @ApiModelProperty(value = "配置值的类型，字典id",notes = "字典组编码java_type",required = true)
    private String valueTypeDictId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
