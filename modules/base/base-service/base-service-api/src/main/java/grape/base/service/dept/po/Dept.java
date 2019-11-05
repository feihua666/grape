package grape.base.service.dept.po;

import com.baomidou.mybatisplus.annotation.TableName;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_dept")
public class Dept extends TreeBasePo<Dept> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 类型,字典id
     */
    private String typeDictId;

    /**
     * 负责人用户id，该id可用来填充审批人
     */
    private String masterUserId;

    /**
     * 公司id
     */
    private String compId;

    /**
     * 是否虚拟部门
     */
    private Boolean isVirtual;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
