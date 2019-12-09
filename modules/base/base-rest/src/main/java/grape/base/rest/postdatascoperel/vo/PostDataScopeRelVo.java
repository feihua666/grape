package grape.base.rest.postdatascoperel.vo;
import grape.common.rest.vo.BaseIdVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 岗位数据范围关系表，多对多
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="岗位数据范围关系表，多对多数据响应对象")
public class PostDataScopeRelVo extends BaseIdVo<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位id")
    private String postId;

    @ApiModelProperty(value = "数据范围id")
    private String dataScopeId;


}
