package grape.base.rest.userpost.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="UserPostListPageForm分页查询条件对象", description="用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职")
public class UserPostListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "公司id，冗余字段，由dept_id派生，")
    private String compId;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "岗位id")
    private String postId;

    @ApiModelProperty(value = "职级id")
    private String jobLevelId;

    @ApiModelProperty(value = "是否有效")
    private Boolean isEffect;

    @ApiModelProperty(value = "无效原因")
    private String ineffectReason;

    @ApiModelProperty(value = "生效的时间")
    private String effcetAt;

    @ApiModelProperty(value = "是否主岗")
    private Boolean isMain;


}
