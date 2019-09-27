package grape.base.rest.func.form;
import grape.common.rest.form.BaseForm;

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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="FuncUpdateForm更新表单对象", description="菜单功能表")
public class FuncUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @ApiModelProperty(value = "类型,字典id")
    private Long typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
