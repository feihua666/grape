package grape.base.rest.user.mvc;

import grape.base.rest.user.form.pwd.UserPwdResetForm;
import grape.base.rest.user.form.pwd.UserPwdUpdateForm;
import grape.base.rest.user.vo.UserPwdVo;
import grape.common.service.loginuser.LoginUser;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.po.UserPwd;
import grape.common.exception.runtime.BadRequestException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.mvc.BaseLoginUserController;
import grape.common.service.common.dataconstraint.DefaultDataObject;
import grape.common.service.common.dataconstraint.IDataObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 用户密码 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/userpwd")
@Api(tags = "用户密码相关接口")
public class UserPwdController extends BaseLoginUserController<UserPwdVo,UserPwd,LoginUser> {

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 默认的数据对象编码
    public static final IDataObject<?> defaultDataObjectCode = new DefaultDataObject("dataObjectCodeUserPwd");

    @Autowired
    private IUserPwdService iUserPwdService;

    /**
     * 开启全局
     * @return
     */
    @Override
    public boolean isEnableDefaultDataObject() {
        // 判断是否存在关闭的情况
        if (getEnableDefaultDataObjectKeyValue() != null) {
            return (boolean) getEnableDefaultDataObjectKeyValue();
        }
        enableDefaultDataObject();
        return super.isEnableDefaultDataObject();
    }

    @Override
    protected String defaultDataObjectCode() {
        return defaultDataObjectCode.dataObjectCode();
    }

    @ApiOperation("重置用户密码")
    @PreAuthorize("hasAuthority('userpwd:single:reset')")
    @PutMapping("/resetPwd")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean resetPwd(@RequestBody @Valid UserPwdResetForm rf) {

        UserPwd userPwd = iUserPwdService.getByUserId(rf.getUserId());
        if (userPwd == null) {
            throw new RBaseException("该用户没有添加密码，不能重置");
        }

        userPwd.setPwd(passwordEncoder.encode(rf.getPwd()));
        userPwd.setPwdModifiedAt(System.currentTimeMillis());
        try {
            update(userPwd);
        } catch (Exception e) {
            throw new RBaseException("重置用户密码失败");
        }

        return true;
    }


     @ApiOperation("用户自己修改密码")
     @PreAuthorize("hasAuthority('user')")
     @PutMapping("/update")
     @ResponseStatus(HttpStatus.CREATED)
     public Boolean update(@RequestBody @Valid UserPwdUpdateForm uf) {

         UserPwd userPwd = iUserPwdService.getByUserId(getLoginUserId());

         if (!passwordEncoder.matches(uf.getOldPassword(),userPwd.getPwd())) {
             throw new BadRequestException("原密码不正确");
         }
         userPwd.setPwd(passwordEncoder.encode(uf.getNewPassword()));
         userPwd.setPwdModifiedAt(System.currentTimeMillis());
         if (!iUserPwdService.updateById(userPwd)) {
             throw new RBaseException("密码修改失败");
         }
         return true;
     }

}
