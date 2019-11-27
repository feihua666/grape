package grape.base.rest.user.vo;
import grape.base.service.dict.api.IDictService;
import grape.base.service.user.api.IUserService;
import grape.common.rest.vo.BaseIdVo;
import grape.common.rest.vo.BaseVo;

import grape.common.service.trans.TransBy;
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
@ApiModel(value="用户登录帐号数据响应对象")
public class UserIdentifierVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户ID")
    private String userId;

    @TransBy(type = IUserService.trans_type_userNickname,byFieldName = "userId")
    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "登录帐号")
    private String identifier;

    @ApiModelProperty(value = "登录帐号类型,字典id")
    private String identityTypeDictId;

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "identityTypeDictId")
    @ApiModelProperty(value = "登录帐号类型,字典编码")
    private String identityTypeDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "identityTypeDictId")
    @ApiModelProperty(value = "登录帐号类型,字典名称")
    private String identityTypeDictName;

    @ApiModelProperty(value = "锁定状态")
    private Boolean isLock;

    @ApiModelProperty(value = "锁定原因")
    private String lockReason;

}
