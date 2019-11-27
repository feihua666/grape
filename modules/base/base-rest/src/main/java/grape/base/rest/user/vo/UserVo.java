package grape.base.rest.user.vo;

import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
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
 * 后台管理用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户数据响应对象")
public class UserVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String nickname;

    @ApiModelProperty(value = "性别，字典id")
    private String genderDictId;

    @TransBy(type = IDictService.trans_dictCode,byFieldName = "genderDictId")
    @ApiModelProperty(value = "性别，字典编码")
    private String genderDictCode;

    @TransBy(type = IDictService.trans_dictName,byFieldName = "genderDictId")
    @ApiModelProperty(value = "性别，字典名称")
    private String genderDictName;

    @ApiModelProperty(value = "头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty(value = "用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @TransBy(type = IDeptService.trans_deptName,byFieldName = "deptId")
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "公司id")
    private String compId;

    @TransBy(type = ICompService.trans_compName,byFieldName = "compId")
    @ApiModelProperty(value = "公司名称")
    private String compName;

    @ApiModelProperty(value = "是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @ApiModelProperty(value = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty(value = "锁定原因")
    private String lockReason;


}
