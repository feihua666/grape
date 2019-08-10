package grape.base.rest.mvc;
import grape.common.rest.vo.BaseVo;
import grape.common.service.po.NormalBasePo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yangwei
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_user")
@ApiModel(value="BaseUserPoVo数据响应对象", description="用户表")
public class BaseUserPoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称，姓名")
    private String nickname;

    @ApiModelProperty(value = "性别，male=男；female=女；unknown=未知；secret=保密")
    private String gender;

    @ApiModelProperty(value = "头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty(value = "用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty(value = "锁定状态，0=未锁定；1=锁定")
    private String isLock;

    @ApiModelProperty(value = "用户所在机构的id")
    private Long officeId;

    @ApiModelProperty(value = "用户所在公司的id，该id也是机构表的一个id，只是类型不同")
    private Long companyId;


}
