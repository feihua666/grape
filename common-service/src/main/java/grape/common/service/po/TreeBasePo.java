package grape.common.service.po;

/**
 * 树关系实体基类
 * Created by yangwei
 * Created at 2019/7/24 17:41
 */
public class TreeBasePo extends BasePo {
    /**
     * 默认根父级id
     */
    public static final String defaultRootParentId = "0";
    /**
     * 默认父级id
     */
    public static final String defaultParentIdX = "0";

    /**
     * 默认深度等级从1开始
     */
    public static final int defaultRootLevel = 1;

    /**
     * 最大支持深度11级
     */
    public static final int maxLevel = 11;
    /**
     * 树深度等级，最顶级是1
     */
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
