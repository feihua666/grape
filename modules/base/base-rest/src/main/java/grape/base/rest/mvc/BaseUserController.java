package grape.base.rest.mvc;


import grape.base.service.api.po.BaseUser;
import grape.base.service.api.service.IBaseUserService;
import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.mvc.BaseController;
import grape.common.rest.vo.BaseVo;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台管理系统用户表 前端控制器
 * </p>
 *
 * @author yangwei
 * @since 2019-07-24
 */
@RestController
@RequestMapping("/base-user")
public class BaseUserController extends BaseController<IBaseUserService, BaseUserController.Vo, BaseUser, CreateForm,BaseUserController.UpdateForm,BaseUserController.ListForm> {

    @Override
    public BaseUser createFormToPo(CreateForm cf) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(cf.getUsername());
        baseUser.setNickname(cf.getNickname());
        baseUser.setGender(cf.getGender());
        baseUser.setAvatar(cf.getAvatar());
        baseUser.setSerialNo(cf.getSerialNo());
        baseUser.setIsLock(cf.getIsLock());
        baseUser.setOfficeId(0L);
        baseUser.setCompanyId(cf.getCompanyId());
        baseUser.setPsw(cf.getPsw());
        /*baseUser.setSalt();
        baseUser.setIsDel();
        baseUser.setCreateAt();
        baseUser.setCreateBy();
        baseUser.setUpdateAt();
        baseUser.setUpdateBy();
        baseUser.setVersion();
        baseUser.setId();*/
        return baseUser;

    }

    @Override
    public BaseUser updateFormToPo(UpdateForm uf) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(uf.getUsername());
        baseUser.setNickname(uf.getNickname());
        baseUser.setGender(uf.getGender());
        baseUser.setAvatar(uf.getAvatar());
        baseUser.setSerialNo(uf.getSerialNo());
        baseUser.setIsLock(uf.getIsLock());
        baseUser.setOfficeId(uf.getOfficeId());
        baseUser.setCompanyId(uf.getCompanyId());
        baseUser.setPsw(uf.getPsw());
        return baseUser;

    }

    @Override
    public BaseUser listFormToPo(ListForm lf) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(lf.getUsername());
        baseUser.setNickname(lf.getNickname());
        return baseUser;

    }

    @Override
    public Vo poToVo(BaseUser po) {
        Vo vo = new Vo();
        vo.setUsername(po.getUsername());
        vo.setNickname(po.getNickname());
        vo.setGender(po.getGender());
        vo.setAvatar(po.getAvatar());
        vo.setSerialNo(po.getSerialNo());
        vo.setIsLock(po.getIsLock());
        vo.setOfficeId(po.getOfficeId());
        vo.setCompanyId(po.getCompanyId());
        return vo;
    }

    @Data
    public class UpdateForm extends BaseForm {

        /**
         * 用户名，用来登录
         */
        private String username;

        /**
         * 昵称，姓名
         */
        private String nickname;

        /**
         * 性别，male=男；female=女；unknown=未知；secret=保密
         */
        private String gender;

        /**
         * 头像，图片绝对路径
         */
        private String avatar;

        /**
         * 用户编号，可以做为员工编号
         */
        private String serialNo;

        /**
         * 锁定状态，0=未锁定；1=锁定
         */
        private String isLock;

        /**
         * 用户所在机构的id
         */
        private Long officeId;

        /**
         * 用户所在公司的id，该id也是机构表的一个id，只是类型不同
         */
        private Long companyId;

        /**
         * 密码
         */
        private String psw;
    }
    @Data
    public class ListForm extends BasePageForm {

        private String username;

        private String nickname;
    }
    @Data
    public class Vo extends BaseVo{

        /**
         * 用户名，用来登录
         */
        private String username;

        /**
         * 昵称，姓名
         */
        private String nickname;

        /**
         * 性别，male=男；female=女；unknown=未知；secret=保密
         */
        private String gender;

        /**
         * 头像，图片绝对路径
         */
        private String avatar;

        /**
         * 用户编号，可以做为员工编号
         */
        private String serialNo;

        /**
         * 锁定状态，0=未锁定；1=锁定
         */
        private String isLock;

        /**
         * 用户所在机构的id
         */
        private Long officeId;

        /**
         * 用户所在公司的id，该id也是机构表的一个id，只是类型不同
         */
        private Long companyId;
    }
}
