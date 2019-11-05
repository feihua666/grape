package grape.base.rest.user.form;
import grape.common.rest.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 更新用户基本信息表单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="更新用户基本信息表单")
public class UserUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "昵称不能为空")
    @NotEmpty
    @ApiModelProperty(value = "昵称，姓名")
    private String nickname;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别",notes = "通过字典获取，字典组编码为gender")
    private String genderDictId;

    @ApiModelProperty(value = "用户编号",notes = "可以做为员工编号")
    private String serialNo;

}
