package grape.base.rest.dict.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表,提供值与编码映射，用于下拉框或组合选择使用
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="DictListPageForm分页查询条件对象", description="字典表,提供值与编码映射，用于下拉框或组合选择使用")
public class DictListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据值")
    private String code;

    @ApiModelProperty(value = "标签名")
    private String name;

    @ApiModelProperty(value = "是否为系统字典，一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @ApiModelProperty(value = "是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @ApiModelProperty(value = "是否为字典组，不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisable;

    @ApiModelProperty(value = "禁用原因")
    private String disableReason;

    @ApiModelProperty(value = "公司id，标识字典归属于哪个公司")
    private Long compId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
