package grape.base.rest.user.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户密码表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="UserPwdListPageForm分页查询条件对象", description="用户密码表")
public class UserPwdListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID,base_user表的主键")
    private String userId;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "授权类型,字典id")
    private String salt;

    @ApiModelProperty(value = "密码状态，字典")
    private String pwdStatusDictId;

    @ApiModelProperty(value = "密码的修改时间")
    private String pwdModifiedAt;


}
