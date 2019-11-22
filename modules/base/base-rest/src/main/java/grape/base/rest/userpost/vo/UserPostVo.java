package grape.base.rest.userpost.vo;
import grape.common.rest.vo.BaseIdVo;

import grape.common.service.trans.Trans;
import grape.common.service.trans.impl.DatetimeTransServiceImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)

@Accessors(chain = true)
@ApiModel(value="用户岗位数据响应对象")
public class UserPostVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "公司id")
    private String compId;

    @ApiModelProperty(value = "公司名称")
    private String compName;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "岗位id")
    private String postId;
    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "职务id")
    private String jobId;
    @ApiModelProperty(value = "职务名称")
    private String jobName;

    @ApiModelProperty(value = "职级id")
    private String jobLevelId;
    @ApiModelProperty(value = "职级名称")
    private String jobLevelName;

    @ApiModelProperty(value = "是否有效")
    private Boolean isEffect;

    @ApiModelProperty(value = "无效原因")
    private String ineffectReason;

    @ApiModelProperty(value = "生效的时间")
    private Long effcetAt;

    @Trans(type = DatetimeTransServiceImpl.trans_datetime,keyFieldName = "effcetAt")
    @ApiModelProperty(value = "生效的时间")
    private String effcetAtFormat;

    @ApiModelProperty(value = "是否主岗")
    private Boolean isMain;


}
