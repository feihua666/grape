package grape.base.service.job.po;

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
 * 职务表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_job")
public class Job extends NormalBasePo<Job> {

    private static final long serialVersionUID = 1L;

    /**
     * 职务编码
     */
    private String code;

    /**
     * 职务名称
     */
    private String name;

    /**
     * 是否公共
     */
    private Boolean isPublic;

    /**
     * 类型，字典id
     */
    private String typeDictId;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
