package grape.base.service.workcalendar.po;

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
 * 工作日历表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_work_calendar")
public class WorkCalendar extends NormalBasePo<WorkCalendar> {

    private static final long serialVersionUID = 1L;

    /**
     * 年
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    /**
     * 日
     */
    private Integer day;

    /**
     * 纯文本，内容，一般为额外添加了福利假或本来是法定节假日但不按国家规定
     */
    private String content;

    /**
     * 类型，字典id
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
