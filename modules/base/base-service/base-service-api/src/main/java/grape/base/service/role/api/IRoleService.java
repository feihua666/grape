package grape.base.service.role.api;

import grape.base.service.role.po.Role;
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
    List<Role> getByUserId(Long userId);
}
