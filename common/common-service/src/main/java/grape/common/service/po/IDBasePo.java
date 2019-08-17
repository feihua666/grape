package grape.common.service.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import grape.common.pojo.BasePojo;
import lombok.Data;

/**
 * 数据库持久化po实体基类，带id，所有数据库表对应的一个父类
 * Created by yangwei
 * Created at 2019/7/22 15:55
 */
@Data
public class IDBasePo<Pk,T extends Model<?>> extends BasePo<T> {

    /**
     *
     * 不存在的id
     * 这算一个规则吧，用来填充默认值
     * 所以的id应该不存在的id
     */
    public static final Long defaultNotExistId = 0L;
    public static final String defaultNotExistIdStr = "0";

    private Pk id;
}