package grape.base.rest.func.vo;
import grape.common.rest.vo.BaseVo;

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
@ApiModel(value="FuncVo数据响应对象", description="菜单功能表")
public class FuncVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

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

    @ApiModelProperty(value = "排序")
    private Integer seq;


}
