package grape.base.rest.job.vo;
import grape.common.rest.vo.BaseIdVo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职务数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="职务数据响应对象")
public class JobVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职务编码")
    private String code;

    @ApiModelProperty(value = "职务名称")
    private String name;

    @ApiModelProperty(value = "是否公共")
    private Boolean isPublic;

    @ApiModelProperty(value = "类型，字典id")
    private String typeDictId;

    @ApiModelProperty(value = "类型，字典编码")
    private String typeDictCode;

    @ApiModelProperty(value = "类型，字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "描述")
    private String remark;


}
