package grape.base.service.orgname.po;

import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织树名称表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_org_name")
public class OrgName extends NormalBasePo<OrgName> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
