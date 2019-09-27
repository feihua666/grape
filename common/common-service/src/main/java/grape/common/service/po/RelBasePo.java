package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import grape.common.pojo.BasePojo;
import lombok.Data;

/**
 * 单纯关系表实体基类
 * 如：用户角色关系表，只有两个字段，user_id,role_id
 * Created by yangwei
 * Created at 2019/7/24 18:03
 */
@Data
public class RelBasePo<T extends Model<?>> extends IDBasePo<Long,T> {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createAt;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
}
