package grape.base.service.func.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.TreeBasePo;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_func")
public class Func extends TreeBasePo<Func> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码，用来查询或系统判断
     */
    private String code;

    /**
     * 标签名
     */
    private String name;

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
    private Long typeDictId;

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
