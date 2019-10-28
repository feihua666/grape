package grape.base.rest.dict.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 字典添加表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="字典添加表单对象")
public class DictCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty(value = "字典编码")
    private String code;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "标签名/字典名称")
    private String name;


    @ApiModelProperty(value = "父id")
    private String parentId;

    @NotNull(message = "是否为系统字典不能为空")
    @ApiModelProperty(value = "是否为系统字典",notes = "一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @NotNull(message = "是否为公共字典不能为空")
    @ApiModelProperty(value = "是否为公共字典",notes = "如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @NotNull(message = "是否为字典组不能为空")
    @ApiModelProperty(value = "是否为字典组",notes = "不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty(value = "公司id",notes = "标识字典归属于哪个公司")
    private String compId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序",notes = "默认按该字段升序排序")
    private Integer seq;


}
