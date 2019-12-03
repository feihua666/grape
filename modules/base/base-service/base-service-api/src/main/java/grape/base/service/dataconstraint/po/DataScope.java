package grape.base.service.dataconstraint.po;

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
 * 数据范围约束表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_data_scope")
public class DataScope extends NormalBasePo<DataScope> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据范围约束编码
     */
    private String code;

    /**
     * 数据范围约束名称
     */
    private String name;

    /**
     * 数据对象id
     */
    private String dataObjectId;

    /**
     * 约束条件，暂时想到的用sql模板
     */
    private String constraintContent;

    /**
     * 是否自定义，如果自定义=1，否则为0
     */
    private Boolean isCustom;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
