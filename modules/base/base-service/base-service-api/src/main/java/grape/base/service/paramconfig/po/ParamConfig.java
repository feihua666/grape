package grape.base.service.paramconfig.po;

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
 * 参数配置表
 * </p>
 *
 * @author yangwei
 * @since 2019-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_param_config")
public class ParamConfig extends NormalBasePo<ParamConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称，姓名
     */
    private String code;

    /**
     * 参数配置名称
     */
    private String name;

    /**
     * 参数配置值
     */
    private String value;

    /**
     * 配置值的类型，字典id
     */
    private String valueTypeDictId;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 禁用/启用原因
     */
    private String disabledReason;

    /**
     * 描述
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
