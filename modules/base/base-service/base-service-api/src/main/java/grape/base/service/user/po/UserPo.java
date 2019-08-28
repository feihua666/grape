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
 * 后台管理用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user")
public class UserPo extends NormalBasePo<UserPo> {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称，姓名
     */
    private String nickname;

    /**
     * 性别，字典id
     */
    private Long genderDictId;

    /**
     * 头像，图片绝对路径
     */
    private String avatar;

    /**
     * 用户编号，可以做为员工编号
     */
    private String serialNo;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 是否虚拟用户，虚拟用户代表不是一个真正存在的用户
     */
    private Boolean isVirtual;

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
