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

    public static <T>  T getLoginUser(){
         T loginUser = (T) ThreadContextTool.get(loginUserKey);
         return loginUser;
    }
    public static   <T>  T removeLoginUser(){
        return (T) ThreadContextTool.remove(loginUserKey);
    }
    public static <T>  void setLoginUser(T loginUser){
        ThreadContextTool.put(loginUserKey,loginUser);
    }
}
