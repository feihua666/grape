package grape.base.rest.workcalendar.form;
import grape.common.rest.form.BaseForm;

import grape.common.rest.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * <p>
 * 工作日历表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="工作日历添加表单对象")
public class WorkCalendarCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;
    @NotNull(message="年不能为空")
    @Range(min = 1900,message = "年范围不正确")
    @ApiModelProperty(value = "年",required = true)
    private Integer year;

    @NotNull(message="月不能为空")
    @Range(max = 12,min = 1,message = "月范围不正确")
    @ApiModelProperty(value = "月",required = true)
    private Integer month;

    @NotNull(message="日不能为空")
    @Range(max = 31,min = 1,message = "日范围不正确")
    @ApiModelProperty(value = "日",required = true)
    private Integer day;

    @ApiModelProperty(value = "纯文本，内容")
    private String content;

    @NotEmpty(message="类型，字典id不能为空")
    @ApiModelProperty(value = "类型，字典id",required = true)
    private String typeDictId;

    @ApiModelProperty(value = "描述")
    private String remark;



}
