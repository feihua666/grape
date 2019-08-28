package grape.base.rest.user.form;
import grape.common.rest.form.BasePageForm;

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
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UserPoListPageForm分页查询条件对象", description="后台管理用户表")
public class UserPoListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String nickname;

    @ApiModelProperty(value = "性别，字典id")
    private Long genderDictId;

    @ApiModelProperty(value = "头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty(value = "用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @ApiModelProperty(value = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty(value = "锁定原因")
    private String lockReason;


}
