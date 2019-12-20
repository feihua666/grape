package grape.base.service.org.po;

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
 * 组织树表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_org")
public class Org extends TreeBasePo<Org> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 组织树名称id
     */
    private String orgNameId;

    /**
     * 关联公司id
     */
    private String relationCompId;

    /**
     * 关联部门id
     */
    private String relationDeptId;

    /**
     * 类型
     */
    private String typeDictId;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
