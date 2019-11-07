package grape.base.rest.log.form;
import grape.common.rest.form.BasePageForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author yangwei
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="系统日志分页查询条件对象")
public class LogListPageForm extends BasePageForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请求id，后台自动生成")
    private String requestId;

    @ApiModelProperty(value = "日志类型,字典")
    private String typeDictId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "操作用户昵称，姓名，冗余字段方便查询")
    private String userNickname;

    @ApiModelProperty(value = "日志内容")
    private String content;

    @ApiModelProperty(value = "请求IP")
    private String host;

    @ApiModelProperty(value = "请求结果状态码")
    private String responseHttpStatus;

    @ApiModelProperty(value = "请求方法")
    private String requestHttpMethod;

    @ApiModelProperty(value = "请求参数")
    private String requestParams;

    @ApiModelProperty(value = "请求头")
    private String requestHttpHeader;

    @ApiModelProperty(value = "请求url")
    private String requestHttpUrl;

    @ApiModelProperty(value = "操作名称")
    private String operationName;

    @ApiModelProperty(value = "接口名称")
    private String interfaceName;

    @ApiModelProperty(value = "执行时长（毫秒）")
    private String duration;


}
