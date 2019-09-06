package grape.base.rest.user.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录标识表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserIdentifierListPageForm分页查询条件对象", description="用户登录标识表")
public class UserIdentifierListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID,base_user表的主键")
    private Long userId;

    @ApiModelProperty(value = "登录标识")
    private String identifier;

    @ApiModelProperty(value = "授权类型,字典id")
    private Long identityTypeDictId;


}
