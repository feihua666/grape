package grape.base.service.dataconstraint.po;

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
 * 数据范围约束自定义表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_data_scope_custom_rel")
public class DataScopeCustomRel extends RelBasePo<DataScopeCustomRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id，实体数据的id，如用户id
     */
    private String dataId;

    /**
     * 数据范围约束id
     */
    private String dataScopeId;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
