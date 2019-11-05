package grape.common.rest.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带有分页的表单
 * Created by yangwei
 * Created at 2019/7/27 16:25
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BasePageForm extends BaseForm {
    /**
     * 请求页码
     */
    @ApiModelProperty(value = "请求页码，从1开始，不传默认为1")
    private Long current = 1L;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "请求每页条数，不传默认为10")
    private Long size = 10L;

}
