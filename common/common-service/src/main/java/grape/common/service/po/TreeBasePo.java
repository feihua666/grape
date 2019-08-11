package grape.common.service.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 树关系实体基类
 * Created by yangwei
 * Created at 2019/7/24 17:41
 */
@Data
public class TreeBasePo<T extends Model<?>> extends NormalBasePo<T> {
    /**
     * 默认根父级id
     */
    public static final Long defaultRootParentId = 0L;
    public static final String defaultRootParentIdStr = "0";
    /**
     * 默认父级id
     */
    public static final Long defaultParentIdX = 0L;
    public static final String defaultParentIdXStr = "0";

    /**
     * 默认深度等级从1开始
     */
    public static final Integer defaultRootLevel = 1;
    public static final String defaultRootLevelStr = "1";

    /**
     * 最大支持深度11级
     */
    public static final Integer maxLevel = 11;
    /**
     * 树深度等级，最顶级是1
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultRootLevelStr)
    private Integer level;
    /**
     * 直接父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultRootParentIdStr)
    private Long parentId;
    /**
     * level为1的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId1;
    /**
     * level为2的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId2;
    /**
     * level为3的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId3;
    /**
     * level为4的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId4;
    /**
     * level为5的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId5;
    /**
     * level为6的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId6;
    /**
     * level为7的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId7;
    /**
     * level为8的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId8;
    /**
     * level为9的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId9;
    /**
     * level为10的父级id
     */
    @TableField(fill = FieldFill.INSERT)
    @FieldFillDefault(insert = defaultParentIdXStr)
    private Long parentId10;

}
