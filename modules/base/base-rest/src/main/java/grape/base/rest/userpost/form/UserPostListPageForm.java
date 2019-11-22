package grape.base.rest.userpost.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户岗位分页查询条件对象")
public class UserPostListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "公司id")
    private String compId;

    @ApiModelProperty(value = "是否主岗")
    private Boolean isMain;


}
