package grape.base.service.user.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.NormalBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户密码表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_user_pwd")
public class UserPwd extends NormalBasePo<UserPwd> {

    private static final long serialVersionUID = 1L;
    /**
     * 密码状态字典组编码
     */
    public enum PwdStatusDictGroup{
        pwd_status
    }
    /**
     * 密码状态字典项编码
     */
    public  enum PwdStatusDictItem{
        normal,need_update
    }
    /**
     * 用户ID,base_user表的主键
     */
    private String userId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 密码状态，字典
     */
    private String pwdStatusDictId;

    /**
     * 密码的修改时间
     */
    private Long pwdModifiedAt;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
