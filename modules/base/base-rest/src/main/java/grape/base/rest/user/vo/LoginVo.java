package grape.base.rest.user.vo;

import grape.base.service.dict.api.IDictService;
import grape.common.rest.vo.BaseVo;
import grape.common.service.trans.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录帐号表
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

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "identityTypeDictId")
    @ApiModelProperty(value = "帐号类型字典编码")
    private String identityTypeDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "identityTypeDictId")
    @ApiModelProperty(value = "帐号类型字典名称")
    private String identityTypeDictName;

    @ApiModelProperty(value = "授权字符串")
    private String token;

}
