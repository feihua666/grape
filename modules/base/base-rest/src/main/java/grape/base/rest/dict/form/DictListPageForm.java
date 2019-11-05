package grape.base.rest.dict.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据分页查询对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="字典分页查询条件对象")
public class DictListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据值")
    private String code;

    @ApiModelProperty(value = "标签名")
    private String name;

    @ApiModelProperty(value = "是否为系统字典")
    private Boolean isSystem;

    @ApiModelProperty(value = "是否为公共字典")
    private Boolean isPublic;

    @ApiModelProperty(value = "是否为字典组")
    private Boolean isGroup;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisable;

    @ApiModelProperty(value = "公司id")
    private String compId;

    @ApiModelProperty(value = "父id")
    private String parentId;

}
