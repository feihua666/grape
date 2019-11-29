package grape.base.service.user.po;

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
 * 用户登录标识表
 * </p>
 *
 * @author yangwei
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_identifier")
public class UserIdentifier extends NormalBasePo<UserIdentifier> {


    /**
     * 登录账号类型字典组编码
     */
    public enum TypeDictGroup{
        admin_account_type
    }
    /**
     * 登录账号类型字典项编码
     */
    public static enum TypeDictItem{
        account_str,account_email,account_mobile
    }
    // 由于枚举不能在注解中使用，主要是验证表单使用
    // 对应枚举添加了静态字符串
    public static final String type_dict_account_mobile = "account_mobile";
    public static final String type_dict_account_email = "account_email";


    private static final long serialVersionUID = 1L;

    /**
     * 用户ID,base_user表的主键
     */
    private String userId;

    /**
     * 登录标识
     */
    private String identifier;

    /**
     * 授权类型,字典id
     */
    private String identityTypeDictId;

    /**
     * 锁定状态，0=未锁定；1=锁定
     */
    private Boolean isLock;

    /**
     * 锁定原因
     */
    private String lockReason;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
