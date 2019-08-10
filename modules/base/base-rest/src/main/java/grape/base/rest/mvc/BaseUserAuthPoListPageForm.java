package grape.base.rest.mvc;
import grape.common.rest.form.BasePageForm;
import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户认证表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_auth")
@ApiModel(value="BaseUserAuthPoListPageForm分页查询条件对象", description="用户认证表")
public class BaseUserAuthPoListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID,base_user表的主键")
    private Long userId;

    @ApiModelProperty(value = "登录标识")
    private String identifier;

    @ApiModelProperty(value = "密码")
    private String credential;

    @ApiModelProperty(value = "授权类型,字典")
    private String identityType;

    @ApiModelProperty(value = "是否已验证，1=是，0=否")
    private String isVerified;


}
