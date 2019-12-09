package grape.base.rest.dataconstraint.vo;

import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.common.rest.vo.BaseIdVo;
import grape.common.service.trans.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据范围约束虚拟树结点vo
 * 由数据对象和数据范围组成的一个简单树
 * 主要用来各数据分配数据范围使用
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据范围约束树形数据响应对象")
public class DataScopeVirtualTreeNodeVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据范围约束名称")
    private String name;

    @ApiModelProperty(value = "父级id",notes = "第一级为数据对象，该值为空，因为数据范围在数据对象下面")
    private String parentId;

    @ApiModelProperty(value = "是否为数据对象",notes = "标识是否为数据对象，不是数据对象就是数据范围")
    private Boolean isDataObject;

    @ApiModelProperty(value = "是否禁用",notes = "数据对象不允许编辑")
    private Boolean isDisabled;
}
