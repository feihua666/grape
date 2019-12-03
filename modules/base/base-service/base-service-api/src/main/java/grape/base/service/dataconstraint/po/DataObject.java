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
 * 数据对象表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_data_object")
public class DataObject extends NormalBasePo<DataObject> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据对象编码
     */
    private String code;

    /**
     * 数据对象名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
