package grape.base.rest.paramconfig.vo;
import grape.common.rest.vo.BaseIdVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="参数配置数据响应对象")
public class ParamConfigVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String code;

    @ApiModelProperty(value = "参数配置名称")
    private String name;

    @ApiModelProperty(value = "参数配置值")
    private String value;

    @ApiModelProperty(value = "配置值的类型，字典id")
    private String valueTypeDictId;

    @ApiModelProperty(value = "配置值的类型，字典编码")
    private String valueTypeDictCode;

    @ApiModelProperty(value = "配置值的类型，字典名称")
    private String valueTypeDictName;


    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用/启用原因")
    private String disabledReason;


}
