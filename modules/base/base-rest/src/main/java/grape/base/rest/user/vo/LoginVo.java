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
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="登录数据响应对象")
public class LoginVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前登录用户id")
    private String id;

    @ApiModelProperty(value = "帐号类型字典id")
    private String identityTypeDictId;

    @ApiModelProperty(value = "帐号类型字典编码")
    private String identityTypeDictCode;

    @ApiModelProperty(value = "帐号类型字典名称")
    private String identityTypeDictName;

    @ApiModelProperty(value = "授权字符串")
    private String token;

}
