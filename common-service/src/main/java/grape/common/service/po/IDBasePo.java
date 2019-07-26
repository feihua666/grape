package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 数据库持久化po实体基类，带id，所有数据库表对应的一个父类
 * Created by yangwei
 * Created at 2019/7/22 15:55
 */
@Data
public class IDBasePo extends BasePo {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
}
