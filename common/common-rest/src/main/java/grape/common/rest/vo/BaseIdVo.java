package grape.common.rest.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 所有返回数据的json数据带id的包装基类
 * Created by yangwei
 * Created at 2019/7/24 18:25
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseIdVo<Pk> extends BaseVo {
    @ApiModelProperty(value = "id",notes = "主键")
    private Pk id;
    @ApiModelProperty(value = "版本",notes = "更新时乐观锁")
    private Integer version;
}
