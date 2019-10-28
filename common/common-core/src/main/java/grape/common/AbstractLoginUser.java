package grape.common;

import grape.common.pojo.BasePojo;
import grape.common.tools.ThreadContext;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;
import org.mapstruct.ap.shaded.freemarker.template.utility.SecurityUtilities;

/**
 * 当前登录用户封装对象
 * Created by yangwei
 * Created at 2019/9/6 17:54
 */
@Data
@Accessors(chain = true)
public abstract class AbstractLoginUser extends BasePojo {

    public static final String loginUserKey = "loginUserKey";
    // 超级管理员编码，一般根角色编码匹配
    public static final String superadminCode = "superadmin";

    // 用户id
    private String userId;

    private String nickname;

    // 头像
    private String avatar;

    public static   AbstractLoginUser getLoginUser(){
        return (AbstractLoginUser) ThreadContext.get(loginUserKey);
    }
    public static void setLoginUser(AbstractLoginUser loginUser){
        ThreadContext.put(loginUserKey,loginUser);
    }
}
