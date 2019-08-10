package grape.base.service.api.po;

import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
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
public class BaseUserAuthPo extends NormalBasePo<BaseUserAuthPo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID,base_user表的主键
     */
    private Long userId;

    /**
     * 登录标识
     */
    private String identifier;

    /**
     * 密码
     */
    private String credential;

    /**
     * 授权类型,字典
     */
    private String identityType;

    /**
     * 是否已验证，1=是，0=否
     */
    private String isVerified;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
