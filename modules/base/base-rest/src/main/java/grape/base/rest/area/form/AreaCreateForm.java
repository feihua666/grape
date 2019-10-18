package grape.base.rest.area.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 区域添加表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="区域添加表单")
public class AreaCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级id",notes = "添加根节点不传值")
    private Long parentId;


    @NotNull(message = "区域名称不能为空")
    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "拼音，全拼")
    private String spell;

    @ApiModelProperty(value = "拼音，简拼，每个字的首字母")
    private String spellSimple;

    @NotNull(message = "区域类型不能为空")
    @ApiModelProperty(value = "类型，字典id")
    private Long typeDictId;

    @ApiModelProperty(value = "行政区划id，该id来自国家统计")
    private Long adminDivisionId;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "描述、备注")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
