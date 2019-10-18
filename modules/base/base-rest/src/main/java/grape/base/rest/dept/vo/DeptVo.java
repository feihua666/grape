package grape.base.rest.dept.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="DeptVo数据响应对象", description="部门表")
public class DeptVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门编码")
    private String code;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "类型,字典id")
    private Long typeDictId;
    @ApiModelProperty(value = "类型，字典编码")
    private String typeDictCode;
    @ApiModelProperty(value = "类型，字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "负责人用户id，该id可用来填充审批人")
    private Long masterUserId;

    @ApiModelProperty(value = "公司id")
    private Long compId;
    @ApiModelProperty(value = "公司名称")
    private String compName;
    @ApiModelProperty(value = "是否虚拟部门")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述")
    private String remark;


}
