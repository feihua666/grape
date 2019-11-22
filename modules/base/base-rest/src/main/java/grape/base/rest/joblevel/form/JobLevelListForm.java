package grape.base.rest.joblevel.form;

import grape.common.rest.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职务级别不分页查询条件对象
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ApiModel(value="职务级别不分页查询条件对象")
public class JobLevelListForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职务级别编码")
    private String code;

    @ApiModelProperty(value = "职务级别名称")
    private String name;

    @ApiModelProperty(value = "职务id")
    private String jobId;

}
