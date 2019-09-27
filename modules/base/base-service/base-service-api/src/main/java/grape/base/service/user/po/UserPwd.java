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
 * 用户密码表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_pwd")
public class UserPwd extends NormalBasePo<UserPwd> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID,base_user表的主键
     */
    private Long userId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 授权类型,字典id
     */
    private String salt;

    /**
     * 密码状态，字典
     */
    private Long pwdStatusDictId;

    /**
     * 密码的修改时间
     */
    private Long pwdModifiedAt;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
