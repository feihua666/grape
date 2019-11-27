package grape.base.rest.user.mvc;

import grape.base.rest.user.form.pwd.UserPwdUpdateForm;
import grape.base.service.BaseLoginUser;
import grape.base.service.user.api.IUserPwdService;
import grape.base.service.user.po.UserPwd;
import grape.common.exception.runtime.BadRequestException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.common.PasswordAndSalt;
import grape.common.rest.mvc.SuperController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/user-pwd")
@Api(tags = "用户密码相关接口")
public class UserPwdController extends SuperController {

    @Autowired
    private IUserPwdService iUserPwdService;
    // 请在这里添加额外的方法
    //todo





    /************************分割线，以下代码为 用户密码表 单表专用，自动生成谨慎修改**************************************************/

     @ApiOperation("修改密码")
     @RequiresPermissions("user")
     @PutMapping("/update")
     @ResponseStatus(HttpStatus.CREATED)
     public Boolean update(@RequestBody @Valid UserPwdUpdateForm uf) {

         BaseLoginUser loginUser = BaseLoginUser.getLoginUser();
         UserPwd userPwd = iUserPwdService.getByUserId(loginUser.getUserId());
         PasswordAndSalt passwordAndSalt = new PasswordAndSalt();
         passwordAndSalt.setPassword(userPwd.getPwd());
         passwordAndSalt.setSalt(userPwd.getSalt());

         if (!PasswordAndSalt.validatePassword(uf.getOldPassword(),passwordAndSalt)) {
             throw new BadRequestException("原密码不正确");
         }
         PasswordAndSalt newPs = PasswordAndSalt.entryptPassword(uf.getNewPassword());
         userPwd.setPwd(newPs.getPassword());
         userPwd.setSalt(newPs.getSalt());
         userPwd.setPwdModifiedAt(System.currentTimeMillis());
         if (!iUserPwdService.updateById(userPwd)) {
             throw new RBaseException("密码修改失败");
         }
         return true;
     }

}
