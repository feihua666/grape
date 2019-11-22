package grape.base.rest.user.vo;
import grape.common.rest.vo.BaseVo;

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
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户密码数据响应对象")
public class UserPwdVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID,base_user表的主键")
    private String userId;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "密码状态，字典")
    private String pwdStatusDictId;

    @ApiModelProperty(value = "密码的修改时间")
    private Long pwdModifiedAt;


}
