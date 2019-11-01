package grape.base.rest.post.vo;
import grape.common.rest.vo.BaseIdVo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 岗位数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="岗位数据响应对象")
public class PostVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位编码")
    private String code;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "启用/禁用原因")
    private String disabledReason;

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
