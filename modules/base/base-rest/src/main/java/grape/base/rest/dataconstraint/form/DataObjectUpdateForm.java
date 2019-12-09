package grape.base.rest.dataconstraint.form;
import grape.common.rest.form.BaseUpdateForm;

import grape.common.rest.validation.props.PropValid;
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
@PropValid
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据对象更新表单对象")
public class DataObjectUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="数据对象名称不能为空")
    @ApiModelProperty(value = "数据对象名称",required = true)
    private String name;


    @ApiModelProperty(value = "数据范围自定义url")
    private String dataCustomUrl;

    @PropValid.Depend(message = "是否数据懒加载不能为空",dependProp = "dataCustomUrl")
    @ApiModelProperty(value = "是否数据懒加载")
    private Boolean isDataLazy;

    @PropValid.Depend(message = "交互方式不能为空",dependProp = "dataCustomUrl")
    @ApiModelProperty(value = "交互方式")
    private String interviewModeDictId;

    private String tableModeColumns;
}
