package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 数据库持久化po实体基类，带数据创建人、创建时间、更新人、更新时间，所有数据库表对应的一个父类
 * Created by yangwei
 * Created at 2019/7/22 15:58
 */
@Data
public class NormalBasePo extends IDBasePo {

    /**
     * 删除标记，N=未删除；Y=已删除
     */
    @TableLogic
    private String isDel;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 乐观锁字段
     */
    @Version
    private Integer version;
}
