package grape.base.rest.user.vo;

import grape.common.rest.vo.BaseVo;
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
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="登录数据响应对象")
public class LoginVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前用户登录id")
    private Long userId;

    @ApiModelProperty(value = "登录标识类型，帐号、邮箱、手机号等")
    private String identifierType;

    @ApiModelProperty(value = "登录标识类型翻译文本")
    private String identifierTypeTxt;
    @ApiModelProperty(value = "授权字符串")
    private String token;

}
