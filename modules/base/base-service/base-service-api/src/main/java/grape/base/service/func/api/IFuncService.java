package grape.base.service.func.api;

import grape.base.service.func.po.Func;
import grape.common.service.IBaseTreeService;

import java.util.List;

/**
 * <p>
 * 菜单功能表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IFuncService extends IBaseTreeService<Func> {

    List<Func> getByRoleId(Long roleId);
}
