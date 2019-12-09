package grape.base.service.dataconstraint.po;

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
 * 数据对象表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_data_object")
public class DataObject extends NormalBasePo<DataObject> {

    private static final long serialVersionUID = 1L;
    /**
     * 功能类型字典组编码
     */
    public enum InterviewModeDictGroup{
        data_object_custom_interview_mode
    }

    /**
     * 功能类型字典项编码
     */
    public enum InterviewModeDictItem{
        interview_mode_tree,interview_mode_table
    }
    /**
     * 数据对象编码
     */
    private String code;

    /**
     * 数据对象名称
     */
    private String name;

    /**
     * 数据范围自定义时用来绑定自定义数据的url
     */
    private String dataCustomUrl;

    /**
     * 自定义数据是否懒加载
     */
    private Boolean isDataLazy;

    /**
     * 自定义数据交互方式，字典，想到的是表格和树形
     */
    private String interviewModeDictId;

    /**
     * 表格模式列定义
     */
    private String tableModeColumns;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
