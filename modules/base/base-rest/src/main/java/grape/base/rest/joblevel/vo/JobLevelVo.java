package grape.base.rest.joblevel.vo;
import grape.base.service.job.api.IJobService;
import grape.common.rest.vo.BaseIdVo;

import grape.common.service.trans.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职务级别
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="职务级别数据响应对象")
public class JobLevelVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "职务级别编码")
    private String code;

    @ApiModelProperty(value = "职务级别名称")
    private String name;

    @ApiModelProperty(value = "职务id")
    private String jobId;

    @TransBy(type = IJobService.trans_type_jobName,byFieldName = "jobId")
    @ApiModelProperty(value = "职务名称")
    private String jobName;

    @ApiModelProperty(value = "描述")
    private String remark;


}
