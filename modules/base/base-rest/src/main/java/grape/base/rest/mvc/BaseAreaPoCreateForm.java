package grape.base.rest.mvc;
import grape.common.rest.form.BaseForm;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import grape.common.service.po.TreeBasePo;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_area")
@ApiModel(value="BaseAreaPoCreateForm添加表单对象", description="区域表")
public class BaseAreaPoCreateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "拼音，全拼")
    private String spell;

    @ApiModelProperty(value = "拼音，简拼，每个字的首字母")
    private String spellSimple;

    @ApiModelProperty(value = "区域类型，字典配置,province=省，city=市，district=区")
    private String type;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "排序,默认按该字段升序排序")
    private Integer sequence;


}
