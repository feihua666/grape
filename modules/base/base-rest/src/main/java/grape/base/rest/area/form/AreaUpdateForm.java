package grape.base.rest.area.form;
import grape.common.rest.form.BaseForm;

import grape.common.rest.form.BaseUpdateForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="区域更新表单对象")
public class AreaUpdateForm extends BaseUpdateForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级id")
    private String parentId;

    @NotEmpty(message = "区域名称不能为空")
    @ApiModelProperty(value = "区域名称",required = true)
    private String name;

    @ApiModelProperty(value = "行政区划id，该id来自国家统计")
    private String adminDivisionId;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "描述、备注")
    private String remark;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序,默认按该字段升序排序",required = true)
    private Integer seq;


}
