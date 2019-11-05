package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树关系实体基类
 * Created by yangwei
 * Created at 2019/7/24 17:41
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TreeBasePo<T extends Model<?>> extends NormalBasePo<T> {

    public static final String COLUMN_PARENT_ID = "parent_id";
    public static final String PROPERTY_PARENT_ID = "parentId";
    public static final String PROPERTY_LEVEL = "level";
    public static final String COLUMN_LEVEL = "level";
    // 初始最顶级深度是1
    public static final int INIT_LEVEL = 1;
    /**
     * 最大支持深度11级
     */
    public static final Integer maxLevel = 11;
    /**
     * 树深度等级，最顶级是1
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer level;
    /**
     * 直接父级id
     */
    private String parentId;
    /**
     * level为1的父级id
     */
    private String parentId1;
    /**
     * level为2的父级id
     */
    private String parentId2;
    /**
     * level为3的父级id
     */
    private String parentId3;
    /**
     * level为4的父级id
     */
    private String parentId4;
    /**
     * level为5的父级id
     */
    private String parentId5;
    /**
     * level为6的父级id
     */
    private String parentId6;
    /**
     * level为7的父级id
     */
    private String parentId7;
    /**
     * level为8的父级id
     */
    private String parentId8;
    /**
     * level为9的父级id
     */
    private String parentId9;
    /**
     * level为10的父级id
     */
    private String parentId10;

}
