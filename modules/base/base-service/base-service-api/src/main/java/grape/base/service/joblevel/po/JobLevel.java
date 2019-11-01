package grape.base.service.joblevel.po;

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
 * 职务级别表
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_job_level")
public class JobLevel extends NormalBasePo<JobLevel> {

    private static final long serialVersionUID = 1L;

    /**
     * 职务级别编码
     */
    private String code;

    /**
     * 职务级别名称
     */
    private String name;

    /**
     * 职务级别
     */
    private String jobId;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
