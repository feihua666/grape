package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据库持久化po实体基类，带id，所有数据库表对应的一个父类
 * Created by yangwei
 * Created at 2019/7/22 15:55
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class IDBasePo<Pk,T extends Model<?>> extends BasePo<T> {
    public static final String PROPERTY_ID = "id";
    public static final String COLUMN_ID = "id";
    public static final String systemUserId = "1";
    @TableId(type = IdType.ID_WORKER_STR)
    private Pk id;
}
