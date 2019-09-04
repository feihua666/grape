package grape.base.rest.page.vo;
import grape.common.rest.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 页面表，用于自动生成页面元素和关联功能菜单
 * </p>
 *
 * @author yangwei
 * @since 2019-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PagePoVo数据响应对象", description="页面表，用于自动生成页面元素和关联功能菜单")
public class PagePoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页面编码，用于用程序识别")
    private String code;

    @ApiModelProperty(value = "页面名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer seq;


}
