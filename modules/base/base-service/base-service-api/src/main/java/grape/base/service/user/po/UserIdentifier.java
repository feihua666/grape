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
    private Long userId;

    /**
     * 登录标识
     */
    private String identifier;

    /**
     * 授权类型,字典id
     */
    private Long identityTypeDictId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
