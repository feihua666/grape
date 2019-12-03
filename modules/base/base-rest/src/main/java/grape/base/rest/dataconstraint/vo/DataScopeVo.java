package grape.base.rest.dataconstraint.vo;
import grape.common.rest.vo.BaseIdVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据范围约束表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据范围约束表数据响应对象")
public class DataScopeVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据范围约束编码")
    private String code;

    @ApiModelProperty(value = "数据范围约束名称")
    private String name;

    @ApiModelProperty(value = "数据对象id")
    private String dataObjectId;

    @ApiModelProperty(value = "约束条件，暂时想到的用sql模板")
    private String constraint;

    @ApiModelProperty(value = "是否自定义，如果自定义=1，否则为0")
    private Boolean isCustom;


}
