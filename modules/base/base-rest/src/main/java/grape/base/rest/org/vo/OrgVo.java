package grape.base.rest.org.vo;
import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.dict.api.IDictService;
import grape.base.service.orgname.api.IOrgNameService;
import grape.common.rest.vo.BaseIdVo;

import grape.common.rest.vo.BaseTreeVo;
import grape.common.service.trans.TransBy;
import grape.common.service.trans.TransItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * <p>
 * 组织树表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
@TransItem(type = IDeptService.trans_deptName, byFieldName = "parentId",forFieldName = "parentName")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="组织树表数据响应对象")
public class OrgVo extends BaseTreeVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织树名称id")
    private String orgNameId;

    @TransBy(type = IOrgNameService.trans_orgNameName,byFieldName = "orgNameId")
    @ApiModelProperty(value = "组织树名称的名称")
    private String orgNameName;

    @ApiModelProperty(value = "关联公司id")
    private String relationCompId;

    @TransBy(type = ICompService.trans_compName,byFieldName = "relationCompId")
    @ApiModelProperty(value = "关联公司名称")
    private String relationCompName;

    @ApiModelProperty(value = "关联部门id")
    private String relationDeptId;

    @TransBy(type = IDeptService.trans_deptName,byFieldName = "relationDeptId")
    @ApiModelProperty(value = "关联部门名称")
    private String relationDeptname;

    @ApiModelProperty(value = "类型字典id")
    private String typeDictId;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "typeDictId")
    @ApiModelProperty(value = "类型字典名称")
    private String typeDictName;

    @ApiModelProperty(value = "描述")
    private String remark;

}
