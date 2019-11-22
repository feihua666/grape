package grape.base.rest.userpost.form;
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
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户岗位添加表单对象")
public class UserPostCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private String userId;


    @NotEmpty(message = "公司id不能为空")
    @ApiModelProperty(value = "公司id")
    private String compId;

    @NotEmpty(message = "部门id不能为空")
    @ApiModelProperty(value = "部门id")
    private String deptId;

    @NotEmpty(message = "岗位id不能为空")
    @ApiModelProperty(value = "岗位id")
    private String postId;

    @NotEmpty(message = "职务id不能为空")
    @ApiModelProperty(value = "职务id")
    private String jobId;

    @NotEmpty(message = "职级id不能为空")
    @ApiModelProperty(value = "职级id")
    private String jobLevelId;

    @NotNull(message = "是否主岗不能为空")
    @ApiModelProperty(value = "是否主岗")
    private Boolean isMain;

    @NotNull(message = "是否有效不能为空")
    @ApiModelProperty(value = "是否有效")
    private Boolean isEffect;

    @ApiModelProperty(value = "无效原因")
    private String ineffectReason;

    @ApiModelProperty(value = "生效的日期时间戳")
    private Long effcetAt;

}
