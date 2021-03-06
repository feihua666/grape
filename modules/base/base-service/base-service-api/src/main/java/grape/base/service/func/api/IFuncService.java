package grape.base.service.func.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.func.po.Func;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.trans.ITransService;

import java.util.List;

/**
 * <p>
 * 菜单功能表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IFuncService extends IBaseTreeService<Func>, ITransService<String,String> {
    public static final String trans_funcName = "funcName";
    public static final String trans_funcCode = "funcCode";
    public static final String trans_funcParentName = "funcParentName";

    /**
     * 根据角色id查询角色关联的功能
     * @param roleId
     * @param funcQuery null 忽略该条件
     * @return
     */
    List<Func> getByRoleId(String roleId,Func funcQuery);
    /**
     * 根据用户id查询用户关联的功能
     * @param userId
     * @param funcQuery null 忽略该条件
     * @return
     */
    List<Func> getByUserId(String userId,Func funcQuery);
    /**
     * 根据用户岗位id查询用户岗位关联的功能
     * @param userPostId
     * @param funcQuery null 忽略该条件
     * @return
     */
    List<Func> getByUserPostId(String userPostId,Func funcQuery);

    /**
     * 根据用岗位id查询岗位关联的功能
     * @param postId
     * @param funcQuery null 忽略该条件
     * @return
     */
    List<Func> getByPostId(String postId,Func funcQuery);

    /**
     * 根据类型查询
     * @param dictItems
     * @param funcQuery
     * @return
     */
    List<Func> getByTypes(List<Func.TypeDictItem> dictItems,Func funcQuery);
    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Func func = new Func();
        func.setCode(code);
        int r = count(Wrappers.query(func));
        return r > 0;
    }

    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_funcCode, trans_funcName, trans_funcParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_funcParentName)) {
            Func parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else{
            Func po = getById(key);
            if (po != null) {
                if (isEqual(type,trans_funcName)) {
                    return po.getName();
                }else if (isEqual(type,trans_funcCode)) {
                    return po.getCode();
                }
            }

        }
        return null;
    }
}
