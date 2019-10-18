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

    /**
     * 根据角色id查询角色关联的功能
     * @param roleId
     * @param isDisabled null 忽略该条件
     * @return
     */
    List<Func> getByRoleId(Long roleId,Boolean isDisabled);

    /**
     * 根据角色id查询角色关联的功能,只包含类型是菜单和页面的功能
     * @param roleId
     * @param isDisabled null 忽略该条件
     * @return
     */
    List<Func> getMenuAndPageByRoleId(Long roleId,Boolean isDisabled);

    /**
     * 根据类型编码查询
     * @param typeCode 必须是grape.base.service.func.po.Func.TypeDictGroup#func_type下字典项
     * @param isDisabled null 忽略该条件
     * @return
     */
    List<Func> getByTypes(List<String> typeCode,Boolean isDisabled);
}
