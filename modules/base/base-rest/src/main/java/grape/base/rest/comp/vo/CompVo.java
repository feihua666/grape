package grape.base.rest.comp.vo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.user.api.IUserService;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.BaseVo;

import grape.common.service.trans.TransBy;
import grape.common.service.trans.TransItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */

@TransItem(type = ICompService.trans_compName, byFieldName = "parentId",forFieldName = "parentName")

@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="公司数据响应对象")
public class CompVo extends BaseTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司编码")
    private String code;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "类型,字典id")
    private String typeDictId;

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型，字典编码")
    private String typeDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型，字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "负责人用户id，该id可用来填充审批人")
    private String masterUserId;

    @TransBy(type = IUserService.trans_type_userNickname,byFieldName = "masterUserId")
    @ApiModelProperty(value = "负责人名称")
    private String masterUserName;

    @ApiModelProperty(value = "是否虚拟公司")
    private Boolean isVirtual;

    @ApiModelProperty(value = "描述、备注")
    private String remark;


}
