package grape.base.rest.page.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 页面元素表，用于用于描述页面上显示的元素抽象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PageElementPoVo数据响应对象", description="页面元素表，用于用于描述页面上显示的元素抽象")
public class PageElementPoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页面编码，用于用程序识别")
    private String code;

    @ApiModelProperty(value = "页面名称")
    private String name;

    @ApiModelProperty(value = "页面元素类型")
    private Long typeDictId;

    @ApiModelProperty(value = "元素为表单项或表格列有效，字段名称")
    private String fieldName;

    @ApiModelProperty(value = "元素为表单项或表格列有效，字段默认值")
    private String fieldDefaultValue;

    @ApiModelProperty(value = "元素为表单项或表格列有效，字段类型，标识是字符串还是数据字")
    private Long fieldTypeDictId;

    @ApiModelProperty(value = "数据库字段名")
    private String columnName;

    @ApiModelProperty(value = "页面id，标识元素属于哪个页面")
    private Long pageId;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
