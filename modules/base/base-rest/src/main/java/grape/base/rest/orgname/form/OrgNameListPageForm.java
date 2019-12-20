package grape.base.rest.orgname.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织树名称表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="组织树名称分页查询条件对象")
public class OrgNameListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

}
