package grape.base.service.role.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.role.po.Role;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.IBaseTreeService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IRoleService extends IBaseTreeService<Role> {

    /**
     * 根据用户id查询角色
     * 用户与角色的关系表查询
     * @param userId
     * @return
     */
    List<Role> getByUserId(String userId);

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
}
