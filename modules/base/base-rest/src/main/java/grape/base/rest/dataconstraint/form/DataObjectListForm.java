package grape.base.rest.dataconstraint.form;

import grape.common.rest.form.BasePageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据对象
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据对象不分页查询条件对象")
public class DataObjectListForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据对象编码")
    private String code;

    @ApiModelProperty(value = "数据对象名称")
    private String name;


}
