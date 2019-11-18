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
 * @since 2019-11-18
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
    private String userId;

    /**
     * 公司id，冗余字段，由dept_id派生，
     */
    private String compId;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 岗位id
     */
    private String postId;

    /**
     * 职务id，冗余字段，由job_level_id对应的job派生
     */
    private String jobId;

    /**
     * 职级id
     */
    private String jobLevelId;

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
    private String effcetAt;

    /**
     * 是否主岗
     */
    private Boolean isMain;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
