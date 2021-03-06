package grape.common;

import grape.common.pojo.BasePojo;
import grape.common.tools.ThreadContextTool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 当前登录用户封装对象
 * Created by yangwei
 * Created at 2019/9/6 17:54
 */
@Data
@EqualsAndHashCode(callSuper=false)
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

    public boolean superAdmin(){
        return false;
    }

    /**
     * 权限信息
     * @return
     */
    public Set<String> permissions(){
        return null;
    }
    /**
     * 权限信息
     * @return
     */
    public Set<String> roles(){
        return null;
    }

    /**
     * 获取当前登录用户的密钥，主要是用来生成jwt token
     * @return
     */
    public String salt(){
        return null;
    }

    public static   AbstractLoginUser getLoginUser(){
        return (AbstractLoginUser) ThreadContextTool.get(loginUserKey);
    }
    public static   AbstractLoginUser removeLoginUser(){
        return (AbstractLoginUser) ThreadContextTool.remove(loginUserKey);
    }
    public static void setLoginUser(AbstractLoginUser loginUser){
        ThreadContextTool.put(loginUserKey,loginUser);
    }
}
