package grape.base.rest.post.form;
import grape.common.rest.form.BaseForm;

import grape.common.rest.form.BaseUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 岗位更新表单对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="岗位更新表单对象")
public class PostUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "岗位名称",required = true)
    private String name;

    @NotNull(message = "是否公共不能为空")
    @ApiModelProperty(value = "是否公共",required = true)
    private Boolean isPublic;

    @ApiModelProperty(value = "类型，字典id",notes = "字典组编码post_type")
    private String typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;


}
