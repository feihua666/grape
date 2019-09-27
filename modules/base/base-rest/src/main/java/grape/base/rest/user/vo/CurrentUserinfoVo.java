package grape.base.rest.user.vo;

import grape.common.rest.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by yangwei
 * Created at 2019/9/27 15:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="当前登录用户信息")
public class CurrentUserinfoVo extends BaseVo {

    private Long id;
    private String nickname;
    private String avatar;

    private Boolean isSwitchRole;

    private Long compId;
    private Long deptId;

    private Long roleId;
    private Long postId;

}
