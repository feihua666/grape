package grape.base.rest.func.vo;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单功能
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="菜单功能数据响应对象")
public class FuncVo extends BaseTreeVo {

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

    @ApiModelProperty(value = "地址，只有类型为page的才有值")
    private String url;

    @ApiModelProperty(value = "类型,字典id")
    private String typeDictId;

    @ApiModelProperty(value = "类型,字典编码")
    private String typeDictCode;

    @ApiModelProperty(value = "类型,字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer seq;


}
