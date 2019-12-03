package grape.common.rest.mvc;

import grape.common.AbstractLoginUser;
import grape.common.exception.CBaseException;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.vo.BaseTreeVo;
import grape.common.rest.vo.TreeNodeVo;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.common.IDataConstraintService;
import grape.common.service.common.IDataObject;
import grape.common.service.po.TreeBasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 正常业务实体的controller基类,提供一些通用方法
 * @param <Vo> 简单vo
 * @param <Po> po
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BaseTreeLoginUserController<Vo extends BaseTreeVo,Po extends TreeBasePo<Po>,loginUser extends AbstractLoginUser> extends BaseTreeController<Vo,Po> {

    private String  dataObjectCode;

    @Autowired(required = false)
    private IDataConstraintService<loginUser> iDataConstraintService;
    public BaseTreeLoginUserController() {
    }
    public BaseTreeLoginUserController(String dataObjectCode) {
        this.dataObjectCode = dataObjectCode;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    protected loginUser getLoginUser(){
        return (loginUser) AbstractLoginUser.getLoginUser();
    }

    /**
     * 获取当前登录用户Id
     * @return
     */
    protected String getLoginUserId(){
        AbstractLoginUser loginUser = AbstractLoginUser.getLoginUser();
        return loginUser == null? null:loginUser.getUserId();
    }

    /**
     * 是否超级管理员
     * @return
     */
    protected boolean getLoginUserSuperAdmin(){
        AbstractLoginUser loginUser = AbstractLoginUser.getLoginUser();
        return loginUser == null? false:loginUser.superAdmin();
    }

    /**
     * 解析数据范围约束
     * @param dataObjectCode
     * @return
     */
    protected ConstraintContent parseConstraint(String dataObjectCode){
        if (iDataConstraintService == null) {
            throw new RBaseException("iDataConstraintService is null,check config please");
        }
        return iDataConstraintService.parseConstraint(dataObjectCode, getLoginUser());
    }
    /**
     * 解析数据范围约束
     * @return
     */
    protected ConstraintContent parseConstraint(){
        return parseConstraint(this.dataObjectCode);
    }
    /**
     * 解析数据范围约束
     * @param dataObjectCode
     * @return
     */
    protected ConstraintContent parseConstraint(IDataObject dataObjectCode){
        return parseConstraint(dataObjectCode.dataObjectCode());
    }
}
