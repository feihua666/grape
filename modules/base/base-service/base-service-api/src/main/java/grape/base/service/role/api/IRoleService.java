package grape.base.service.role.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.role.po.Role;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.trans.ITransService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IRoleService extends IBaseTreeService<Role> , ITransService<String,String> {
    public static final String trans_roleName = "roleName";
    public static final String trans_roleCode = "roleCode";
    public static final String trans_roleParentName = "roleParentName";
    /**
     * 根据用户id查询角色
     * 用户与角色的关系表查询
     * @param userId
     * @param query null 忽略该条件
     * @return
     */
    List<Role> getByUserId(String userId,Role query);
    /**
     * 根据用户岗位关系id查询角色
     * @param userPostId
     * @param query null 忽略该条件
     * @return
     */
    List<Role> getByUserPostId(String userPostId,Role query);

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Role role = new Role();
        role.setCode(code);
        int r = count(Wrappers.query(role));
        return r > 0;
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_roleCode,trans_roleName, trans_roleParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_roleParentName)) {
            Role parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else{
            Role po = getById(key);
            if (po != null) {
                if (isEqual(type,trans_roleName)) {
                    return po.getName();
                }else if (isEqual(type,trans_roleCode)) {
                    return po.getCode();
                }
            }

        }
        return null;
    }
}
