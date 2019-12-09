package grape.base.rest.dataconstraint.form;
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
 * 数据范围约束表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@ScriptAssert(message = "约束条件不能为空",lang = "javascript",script = "_this.isCustom?true:!!_this.constraintContent")

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据范围约束表更新表单对象")
public class DataScopeUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="数据范围约束名称不能为空")
    @ApiModelProperty(value = "数据范围约束名称",required = true)
    private String name;

    @NotEmpty(message="数据对象id不能为空")
    @ApiModelProperty(value = "数据对象id",required = true)
    private String dataObjectId;

    @ApiModelProperty(value = "约束条件")
    private String constraintContent;

    @NotNull(message="是否自定义不能为空")
    @ApiModelProperty(value = "是否自定义",required = true)
    private Boolean isCustom;

    @ApiModelProperty(value = "备注")
    private String remark;
}
