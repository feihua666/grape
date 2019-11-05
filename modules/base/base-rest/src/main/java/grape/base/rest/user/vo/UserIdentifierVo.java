package grape.base.rest.user.vo;
import grape.common.rest.vo.BaseIdVo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户标识数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户登录标识数据响应对象")
public class UserIdentifierVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "登录标识")
    private String identifier;

    @ApiModelProperty(value = "登录标识类型,字典id")
    private String identityTypeDictId;
    @ApiModelProperty(value = "登录标识类型,字典编码")
    private String identityTypeDictCode;
    @ApiModelProperty(value = "登录标识类型,字典名称")
    private String identityTypeDictName;

}
