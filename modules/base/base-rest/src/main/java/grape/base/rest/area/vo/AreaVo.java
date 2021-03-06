package grape.base.rest.area.vo;
import grape.base.service.area.api.IAreaService;
import grape.base.service.dict.api.IDictService;
import grape.common.rest.vo.BaseTreeVo;

import grape.common.service.trans.TransBy;
import grape.common.service.trans.TransItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@TransItem(type = IAreaService.trans_areaName, byFieldName = "parentId",forFieldName = "parentName")
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="区域数据响应对象")
public class AreaVo extends BaseTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "拼音，全拼")
    private String spell;

    @ApiModelProperty(value = "拼音，首字母")
    private String spellFirst;

    @ApiModelProperty(value = "拼音，简拼，每个字的首字母")
    private String spellSimple;

    @ApiModelProperty(value = "类型，字典id")
    private String typeDictId;

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型，字典编码")
    private String typeDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型，字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "行政区划id，该id来自国家统计")
    private String adminDivisionId;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "描述、备注")
    private String remark;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
