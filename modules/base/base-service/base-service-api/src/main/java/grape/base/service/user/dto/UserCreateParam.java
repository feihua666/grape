package grape.base.service.user.dto;

import grape.common.service.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建用户参数
 * Created by yangwei
 * Created at 2019/11/28 14:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCreateParam extends BaseDto {

    private String account;

    private String password;

    private String salt;

    private String nickname;

    private String genderDictId;

    private String avatar;

    private String serialNo;

    private String deptId;

    private Boolean isVirtual;
}
