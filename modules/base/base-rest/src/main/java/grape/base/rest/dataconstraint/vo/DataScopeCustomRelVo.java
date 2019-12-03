package grape.base.rest.dataconstraint.vo;
import grape.common.rest.vo.BaseIdVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据范围约束自定义表
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="数据范围约束自定义表数据响应对象")
public class DataScopeCustomRelVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据id，实体数据的id，如用户id")
    private String dataId;

    @ApiModelProperty(value = "数据范围约束id")
    private String dataScopeId;


}
