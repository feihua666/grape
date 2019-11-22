package grape.base.rest.post.form;

import grape.common.rest.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职务不分页查询条件对象
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="岗位不分页查询条件对象")
public class PostListForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位编码")
    private String code;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "部门id名称")
    private String deptId;

    @ApiModelProperty(value = "是否包括公共岗位")
    private Boolean isPublic;
}
