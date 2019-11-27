package grape.base.rest.role.vo;
import grape.base.service.role.api.IRoleService;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.BaseVo;

import grape.common.service.trans.TransItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@TransItem(type = IRoleService.trans_roleName, byFieldName = "parentId",forFieldName = "parentName")
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="角色数据响应对象")
public class RoleVo extends BaseTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "描述")
    private String remark;


}
