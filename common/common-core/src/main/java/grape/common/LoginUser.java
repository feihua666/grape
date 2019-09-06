package grape.common;

import grape.common.pojo.BasePojo;
import lombok.Data;

/**
 * 当前登录用户封装对象
 * Created by yangwei
 * Created at 2019/9/6 17:54
 */
@Data
public abstract class LoginUser extends BasePojo {

    // 用户id
    private Long userId;

    // 头像
    private String avatar;

    // 所属公司
    private Long compId;
    // 所属部门
    private Long deptId;
    // 所属岗位
    private Long postId;

    public static LoginUser getLoginUser(){
        return null;
    }
}
