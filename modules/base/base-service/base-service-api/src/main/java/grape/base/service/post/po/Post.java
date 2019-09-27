package grape.base.service.post.po;

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
 * 岗位表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_post")
public class Post extends NormalBasePo<Post> {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 禁用原因
     */
    private String disabledReason;

    /**
     * 是否公共
     */
    private Boolean isPublic;

    /**
     * 类型，字典id
     */
    private Long typeDictId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
