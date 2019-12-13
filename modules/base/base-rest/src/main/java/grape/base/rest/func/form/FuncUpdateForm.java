package grape.base.rest.func.form;
import grape.common.rest.form.BaseForm;

import grape.common.rest.form.BaseUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@ApiModel(value="功能更新表单对象")
public class FuncUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父id",notes = "如果该节点下还有子节点，不允许修改该值，也就是说只允许移动叶子节点")
    private String parentId;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @NotEmpty(message = "应用id不能为空")
    @ApiModelProperty(value = "应用id",required = true)
    private String applicationId;


    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
