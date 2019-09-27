package grape.base.rest.post.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PostVo数据响应对象", description="岗位表")
public class PostVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位编码")
    private String code;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "是否公共")
    private Boolean isPublic;

    @ApiModelProperty(value = "类型，字典id")
    private Long typeDictId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "描述")
    private String remark;


}
