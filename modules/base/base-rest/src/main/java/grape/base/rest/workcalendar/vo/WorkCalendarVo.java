package grape.base.rest.workcalendar.vo;
import grape.base.service.dict.api.IDictService;
import grape.common.rest.vo.BaseIdVo;

import grape.common.service.trans.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="工作日历表数据响应对象")
public class WorkCalendarVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年")
    private Integer year;

    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "日")
    private Integer day;

    @ApiModelProperty(value = "纯文本，内容，一般为额外添加了福利假或本来是法定节假日但不按国家规定")
    private String content;

    @ApiModelProperty(value = "类型，字典id")
    private String typeDictId;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型，字典id")
    private String typeDictName;

    @ApiModelProperty(value = "描述")
    private String remark;


}
