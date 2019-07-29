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
public class NormalBasePo<T extends Model<?>> extends IDBasePo<Long,T> {

    /**
     * 删除标记，0=未删除；1=已删除
     * 该字段不需要自动填充，已被mybatis plus处理
     */
    @TableLogic
    private Char isDel;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = FieldFillDefault.VAR_NOW)
    private Long createAt;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @FieldFillDefault(insert = FieldFillDefault.VAR_NOW,update = FieldFillDefault.VAR_NOW)
    private Long updateAt;

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 乐观锁字段
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = "1")
    private Integer version;
}
