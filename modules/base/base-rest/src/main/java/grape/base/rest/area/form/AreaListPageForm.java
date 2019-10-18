package grape.base.rest.area.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域分页查询表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="区域分页查询表单")
public class AreaListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "类型，字典id")
    private Long typeDictId;

    @ApiModelProperty(value = "行政区划id，该id来自国家统计")
    private Long adminDivisionId;

}
