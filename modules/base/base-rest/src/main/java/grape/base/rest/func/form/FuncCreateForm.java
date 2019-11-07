package grape.base.rest.func.form;
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
 * 菜单功能表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="FuncCreateForm添加表单对象", description="菜单功能表")
public class FuncCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @ApiModelProperty(value = "父id",notes = "不填写为根节点")
    private String parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "shiro权限串，多个以逗号分隔")
    private String permissions;

    @NotEmpty(message = "类型不能为空")
    @ApiModelProperty(value = "类型,字典id",notes = "字典组编码 func_type",required = true)
    private String typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序",notes = "默认为10")
    private Integer seq = 10;


}
