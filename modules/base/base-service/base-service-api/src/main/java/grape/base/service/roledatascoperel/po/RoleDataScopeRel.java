package grape.base.service.roledatascoperel.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.RelBasePo;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色数据范围关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_role_data_scope_rel")
public class RoleDataScopeRel extends RelBasePo<RoleDataScopeRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 数据对象id
     */
    private String dataObjectId;

    /**
     * 数据范围id
     */
    private String dataScopeId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
