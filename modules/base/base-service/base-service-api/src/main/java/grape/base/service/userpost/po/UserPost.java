package grape.base.service.userpost.po;

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
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职
 * </p>
 *
 * @author yangwei
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_post")
public class UserPost extends NormalBasePo<UserPost> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 公司id，冗余字段，由dept_id派生，
     */
    private Long compId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 岗位id
     */
    private Long postId;

    /**
     * 职级id
     */
    private Long jobLevelId;

    /**
     * 是否有效
     */
    private Boolean isEffect;

    /**
     * 无效原因
     */
    private String ineffectReason;

    /**
     * 生效的时间
     */
    private Long effcetAt;

    /**
     * 是否主岗
     */
    private Boolean isMain;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
