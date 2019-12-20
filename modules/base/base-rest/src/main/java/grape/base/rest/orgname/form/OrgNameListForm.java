package grape.base.rest.orgname.form;

import grape.common.rest.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织树名称不分页查询条件对象
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="组织树名称不分页查询条件对象")
public class OrgNameListForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织树名称编码")
    private String code;

    @ApiModelProperty(value = "组织树名称")
    private String name;

}
