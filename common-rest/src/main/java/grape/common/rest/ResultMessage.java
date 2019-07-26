package grape.common.rest;

import grape.common.exception.ExceptionCode;
import grape.common.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应消息
 * Created by yangwei
 * Created at 2019/7/24 18:30
 */
@Data
@ApiModel(value = "ResultMessage", description = "返回结果")
public class ResultMessage<D> extends BasePojo {

    @ApiModelProperty(value = "编码")
    private ExceptionCode code = ExceptionCode.ok;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private D data;

}
