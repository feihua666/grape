package grape.common.service.po;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 数据库持久化po实体基类，带数据创建人、创建时间、更新人、更新时间等，所有数据库表对应的一个父类
 * 该基类的使用是一般的数据表都包含相关字段
 * Created by yangwei
 * Created at 2019/7/22 15:58
 */
@Data
public class NormalBasePo<T extends Model<?>> extends RelBasePo<T> {

    public static final String PROPERTY_CREATE_AT = "createAt";
    public static final String PROPERTY_CREATE_BY = "createBy";
    public static final String PROPERTY_UPDATE_AT = "updateAt";
    public static final String PROPERTY_UPDATE_BY = "updateBy";
    public static final String PROPERTY_VERSION = "version";
    // 初始版本默认值
    public static final int INIT_VERSION = 1;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateAt;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 乐观锁字段
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
