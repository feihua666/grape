package grape.common.rest.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * rest接口表单的基类，所以表单应继承该类
 * Created by yangwei
 * Created at 2019/7/23 13:17
 */
@Data
public class BaseUpdateForm extends BaseForm {

    @ApiModelProperty(value = "版本",notes = "根据信息数据原样返回")
    @NotNull(message = "版本不能为空")
    private Integer version;
}
