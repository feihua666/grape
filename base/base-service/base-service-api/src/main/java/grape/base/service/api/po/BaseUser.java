package grape.base.service.api.po;

import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台管理系统用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseUser extends NormalBasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名，用来登录
     */
    private String username;

    /**
     * 昵称，姓名
     */
    private String nickname;

    /**
     * 性别，male=男；female=女；unknown=未知；secret=保密
     */
    private String gender;

    /**
     * 头像，图片绝对路径
     */
    private String avatar;

    /**
     * 用户编号，可以做为员工编号
     */
    private String serialNo;

    /**
     * 锁定状态，N=未锁定；Y=锁定
     */
    private String isLock;

    /**
     * 用户所在机构的id
     */
    private String officeId;

    /**
     * 用户所在公司的id，该id也是机构表的一个id，只是类型不同
     */
    private String companyId;

    /**
     * 密码
     */
    private String psw;

    /**
     * 密码加盐值
     */
    private String salt;


}
