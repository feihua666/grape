package grape.base.rest.dataconstraint.form;
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
 * 数据对象表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据对象添加表单对象")
public class DataObjectCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;
    @NotEmpty(message="数据对象编码不能为空")
    @ApiModelProperty(value = "数据对象编码",required = true)
    private String code;

    @NotEmpty(message="数据对象名称不能为空")
    @ApiModelProperty(value = "数据对象名称",required = true)
    private String name;



}
