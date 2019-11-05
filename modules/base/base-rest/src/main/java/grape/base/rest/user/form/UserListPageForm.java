package grape.base.rest.user.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台管理用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户分页查询条件对象")
public class UserListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String nickname;

    @ApiModelProperty(value = "性别，字典id")
    private String genderDictId;

    @ApiModelProperty(value = "用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty(value = "部门id")
    private String deptId;

}
