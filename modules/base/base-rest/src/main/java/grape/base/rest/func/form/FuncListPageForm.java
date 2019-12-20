package grape.base.rest.func.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="菜单功能分页查询条件对象")
public class FuncListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "编码，精准查询")
    private String code;

    @ApiModelProperty(value = "名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "类型,字典id")
    private String typeDictId;

}
