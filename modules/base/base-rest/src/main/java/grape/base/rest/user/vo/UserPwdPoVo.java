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
 * @since 2019-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserPwdPoVo数据响应对象", description="用户密码表")
public class UserPwdPoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID,base_user表的主键")
    private Long userId;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "授权类型,字典id")
    private String salt;

    @ApiModelProperty(value = "密码状态，字典")
    private Long pwdStatusDictId;

    @ApiModelProperty(value = "密码的修改时间")
    private Long pwdModifiedAt;


}
