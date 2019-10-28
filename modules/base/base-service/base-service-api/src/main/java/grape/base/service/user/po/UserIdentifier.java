package grape.base.service.user.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.NormalBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("base_user_identifier")
public class UserIdentifier extends NormalBasePo<UserIdentifier> {

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


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
