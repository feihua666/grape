package grape.base.rest.dataconstraint.vo;
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
 * 数据对象表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据对象表数据响应对象")
public class DataObjectVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据对象编码")
    private String code;

    @ApiModelProperty(value = "数据对象名称")
    private String name;


    @ApiModelProperty(value = "数据范围自定义url")
    private String dataCustomUrl;


    @ApiModelProperty(value = "是否数据懒加载")
    private Boolean isDataLazy;

    @ApiModelProperty(value = "交互方式字典id")
    private String interviewModeDictId;

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "interviewModeDictId")
    @ApiModelProperty(value = "交互方式字典编码")
    private String interviewModeDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "interviewModeDictId")
    @ApiModelProperty(value = "交互方式字典名称")
    private String interviewModeDictName;

    @ApiModelProperty(value = "表格交互列定义")
    private String tableModeColumns;
}
