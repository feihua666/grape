package grape.base.rest.paramconfig.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="参数配置分页查询条件对象")
public class ParamConfigListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String code;

    @ApiModelProperty(value = "参数配置名称")
    private String name;

    @ApiModelProperty(value = "配置值的类型，字典id")
    private String valueTypeDictId;
}
