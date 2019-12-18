package grape.base.service.userpostfuncrel.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.RelBasePo;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位功能关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user_post_func_rel")
public class UserPostFuncRel extends RelBasePo<UserPostFuncRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户岗位id
     */
    private String userPostId;

    /**
     * 功能id
     */
    private String funcId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
