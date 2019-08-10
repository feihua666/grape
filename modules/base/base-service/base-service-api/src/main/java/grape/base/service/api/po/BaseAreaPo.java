package grape.base.service.api.po;

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
 * 区域表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_area")
public class BaseAreaPo extends TreeBasePo<BaseAreaPo> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 拼音，全拼
     */
    private String spell;

    /**
     * 拼音，简拼，每个字的首字母
     */
    private String spellSimple;

    /**
     * 区域类型，字典配置,province=省，city=市，district=区
     */
    private String type;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 排序,默认按该字段升序排序
     */
    private Integer sequence;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
