package grape.common.rest.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grape.common.AbstractLoginUser;
import grape.common.exception.ExceptionTools;
import grape.common.exception.runtime.RBaseException;
import grape.common.rest.form.BasePageForm;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintService;
import grape.common.service.common.IDataObject;
import grape.common.service.po.IDBasePo;
import grape.common.tools.ThreadContextTool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 正常业务实体的controller基类,提供一些通用方法
 * @param <Vo> 简单vo
 * @param <Po> po
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class BaseLoginUserController<Vo,Po extends IDBasePo<?,?>,loginUser extends AbstractLoginUser> extends BaseController<Vo,Po> {

    public static final String enableDefaultDataObjectKey = "enableDefaultDataObjectKey";


    @Autowired(required = false)
    private IDataConstraintService<loginUser> iDataConstraintService;

    public BaseLoginUserController() {
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
     * @param dataObjectCode
     * @return
     */
    protected ConstraintContent parseConstraint(IDataObject dataObjectCode){
        return parseConstraint(dataObjectCode.dataObjectCode());
    }
    /**
     * 重写父级方法
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery){
        if(isEnableDefaultDataObject()){
            return update(poQuery, parseConstraint(defaultDataObjectCode()));
        }
        return super.update(poQuery);
    }
    /**
     * 单表更新，重载数据对象约束
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery,String dataObjectCode){
        return update(poQuery, parseConstraint(dataObjectCode));
    }
    /**
     * 单表更新，重载数据对象约束
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery,IDataObject dataObject){
        return update(poQuery, parseConstraint(dataObject));
    }
    /**
     * 单表更新，重载数据对象约束
     * @param poQuery
     * @return
     */

    public Vo update(Po poQuery,ConstraintContent constraintContent){
        boolean r = getService().updateById(poQuery,constraintContent);
        if (!r) {
            throw ExceptionTools.failRE("更新失败，请刷新数据后再试");
        }
        Vo vo = getMapperConverter().poToVo(getService().getById(poQuery.getId().toString()));
        vo = transVo(vo);
        return vo;
    }

    /**
     * 重写父级方法
     * @param id
     * @return
     */
    public boolean deleteById(String id){
        if(isEnableDefaultDataObject()){
            return deleteById(id, parseConstraint(defaultDataObjectCode()));
        }
        return super.deleteById(id);
    }
    /**
     * 单表根据id删除，重载数据对象约束
     * @param id
     * @return
     */

    public boolean deleteById(String id,String dataObjectCode){

        return deleteById(id, parseConstraint(dataObjectCode));
    }
    /**
     * 单表根据id删除，重载数据对象约束
     * @param id
     * @return
     */

    public boolean deleteById(String id,IDataObject dataObject){

        return deleteById(id, parseConstraint(dataObject));
    }
    /**
     * 单表根据id删除，重载数据对象约束
     * @param id
     * @return
     */

    public boolean deleteById(String id,ConstraintContent constraintContent){

        boolean r = getService().removeById(id,constraintContent);
        if (!r) {
            throw ExceptionTools.dataNotExistRE("数据不存在，请刷新后再试");
        }
        // 如果这里返回null，并不会被GlobalResponseBodyAdvice 统一包装
        return r;
    }

    /**
     * 重写父方法
     * @param id
     * @return
     */
    public Vo queryById(String id){
        if(isEnableDefaultDataObject()){
            return queryById(id, parseConstraint(defaultDataObjectCode()));
        }
        return super.queryById(id);
    }
    /**
     * 单表根据id获取，重载数据对象约束
     * @param id
     * @return
     */

    public Vo queryById(String id,String dataObjectCode){
        return queryById(id, parseConstraint(dataObjectCode));
    }
    /**
     * 单表根据id获取，重载数据对象约束
     * @param id
     * @return
     */

    public Vo queryById(String id,IDataObject dataObject){
        return queryById(id, parseConstraint(dataObject));
    }
    /**
     * 单表根据id获取，重载数据对象约束
     * @param id
     * @return
     */

    public Vo queryById(String id,ConstraintContent constraintContent){
        Po dbPo = getService().getById(id,constraintContent);
        Vo vo = getMapperConverter().poToVo(dbPo);
        if (vo == null) {
            throw ExceptionTools.dataNotExistRE(null);
        }
        vo = transVo(vo);
        return vo;
    }

    /**
     * 重写是否启动默认数据对象约束
     * @param poQuery
     * @param pageForm
     * @return
     */
    public IPage<Vo> listPage(Po poQuery, BasePageForm pageForm){
        if(isEnableDefaultDataObject()){
            return listPage(poQuery, pageForm, parseConstraint(defaultDataObjectCode()));
        }
        return super.listPage(poQuery, pageForm);
    }
    /**
     * 分页查询，重载数据对象约束
     * @param poQuery
     * @param pageForm
     * @return
     */
    public IPage<Vo> listPage(Po poQuery, BasePageForm pageForm,String dataObjectCode){
        assertParamNotEmpty(dataObjectCode,"数据对象编码不能为空");
        return listPage(poQuery, pageForm, parseConstraint(dataObjectCode));
    }
    /**
     * 分页查询，重载数据对象约束
     * @param poQuery
     * @param pageForm
     * @return
     */
    public IPage<Vo> listPage(Po poQuery, BasePageForm pageForm,IDataObject dataObject){
        assertNotNull(dataObject,"数据对象编码不能为空");
        return listPage(poQuery, pageForm, parseConstraint(dataObject));
    }
    /**
     * 分页查询，重载数据对象约束
     * @param poQuery
     * @param pageForm
     * @return
     */
    public IPage<Vo> listPage(Po poQuery, BasePageForm pageForm,ConstraintContent constraintContent){
        assertNotNull(constraintContent,"约束内容不能为空");
        IPage<Po> page = getService().page(new Page(pageForm.getCurrent(),pageForm.getSize()),poQuery,constraintContent);
        if (page.getTotal() == 0) {
            throw ExceptionTools.dataNotExistRE("暂无数据");
        }
        return pagePoToVo(page);
    }

    /**
     * 重写父级方法
     * @param poQuery
     * @return
     */
    public List<Vo> list(Po poQuery){
        if(isEnableDefaultDataObject()){
            return list(poQuery, parseConstraint(defaultDataObjectCode()));
        }
        return super.list(poQuery);
    }
    /**
     * 不分页查询，重载数据对象约束
     * @param poQuery
     * @return
     */
    public List<Vo> list(Po poQuery,String dataObjectCode){
        assertParamNotEmpty(dataObjectCode,"数据对象编码不能为空");
        return list(poQuery, parseConstraint(dataObjectCode));
    }
    /**
     * 不分页查询，重载数据对象约束
     * @param poQuery
     * @return
     */
    public List<Vo> list(Po poQuery,IDataObject dataObject){
        assertNotNull(dataObject,"数据对象编码不能为空");
        return list(poQuery, parseConstraint(dataObject));
    }
    /**
     * 不分页查询，重载数据对象约束
     * @param poQuery
     * @return
     */
    public List<Vo> list(Po poQuery,ConstraintContent constraintContent){
        assertNotNull(constraintContent,"约束内容不能为空");
        List list = getService().list(poQuery,constraintContent);
        return posToVos(list);
    }

    /**
     * 启动默认的数据对象编码
     */
    protected void enableDefaultDataObject(){
        // 记得用完从线程变量删除
        ThreadContextTool.put(enableDefaultDataObjectKey,true);
    }
    private boolean isEnableDefaultDataObject(){
        boolean r =  (boolean) ThreadContextTool.get(enableDefaultDataObjectKey);
        ThreadContextTool.remove(enableDefaultDataObjectKey);
        return r;
    }
    /**
     * 默认的数据对象编码，重写该方法，获取默认的数据对象编码
     * @return
     */
    protected String defaultDataObjectCode(){
        return null;
    }
}
