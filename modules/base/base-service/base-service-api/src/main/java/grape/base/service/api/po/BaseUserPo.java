package grape.base.service.api.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import grape.common.service.po.FieldFillDefault;
import grape.common.service.po.IDBasePo;
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
 * 用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user")
public class BaseUserPo extends NormalBasePo<BaseUserPo> {

    private static final long serialVersionUID = 1L;

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
     * 锁定状态，0=未锁定；1=锁定
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = "0")
    private String isLock;

    /**
     * 用户所在机构的id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultNotExistIdStr)
    private Long officeId;

    /**
     * 用户所在公司的id，该id也是机构表的一个id，只是类型不同
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultNotExistIdStr)
    private Long companyId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
