package grape.base.service.func.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.common.IDictGroup;
import grape.common.service.common.IDictItem;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_func")
public class Func extends TreeBasePo<Func> {

    private static final long serialVersionUID = 1L;

    /**
     * 功能类型字典组编码
     */
    public enum TypeDictGroup implements IDictGroup {
        func_type;

        @Override
        public String groupCode() {
            return this.name();
        }}

    /**
     * 功能类型字典项编码
     */
    public enum TypeDictItem implements IDictItem {
        menu,page;

        @Override
        public String itemCode() {
            return this.name();
        }}

    /**
     * 编码，用来查询或系统判断
     */
    private String code;

    /**
     * 标签名
     */
    private String name;

    /**
     * 应用id
     */
    private String applicationId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 禁用原因
     */
    private String disabledReason;

    /**
     * 地址
     */
    private String url;

    /**
     * shiro权限串，多个以逗号分隔
     */
    private String permissions;

    /**
     * 类型,字典id
     */
    private String typeDictId;

    /**
     * 描述
     */
    private String remark;

    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
