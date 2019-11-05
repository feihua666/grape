package grape.base.rest.dict.vo;

import grape.common.rest.vo.BaseTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据响应对象
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="字典数据响应对象")
public class DictVo extends BaseTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据值")
    private String code;

    @ApiModelProperty(value = "标签名")
    private String name;

    @ApiModelProperty(value = "是否为系统字典",notes = "一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @ApiModelProperty(value = "是否为公共字典")
    private Boolean isPublic;

    @ApiModelProperty(value = "是否为字典组",notes = "不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "禁用原因")
    private String disabledReason;

    @ApiModelProperty(value = "公司id",notes = "标识字典归属于哪个公司")
    private String compId;

    @ApiModelProperty(value = "公司名称")
    private String compName;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;

}
