package grape.base.service.area.po;

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
 * @since 2019-11-01
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@TableName("base_area")
public class Area extends TreeBasePo<Area> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 第一个字的首字母
     */
    private String spellFirst;

    /**
     * 拼音，简拼，每个字的首字母
     */
    private String spellSimple;

    /**
     * 拼音，全拼
     */
    private String spell;

    /**
     * 类型，字典id
     */
    private String typeDictId;

    /**
     * 行政区划id，该id来自国家统计
     */
    private String adminDivisionId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 描述、备注
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
