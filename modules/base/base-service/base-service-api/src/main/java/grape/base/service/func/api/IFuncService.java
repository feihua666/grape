package grape.base.service.func.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.func.po.Func;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseTreeService;

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
    List<Func> getByRoleId(String roleId,Boolean isDisabled);

    /**
     * 根据角色id查询角色关联的功能,只包含类型是菜单和页面的功能
     * @param roleId
     * @param isDisabled null 忽略该条件
     * @return
     */
    List<Func> getMenuAndPageByRoleId(String roleId,Boolean isDisabled);

    /**
     * 根据类型编码查询
     * @param typeCode 必须是grape.base.service.func.po.Func.TypeDictGroup#func_type下字典项
     * @param isDisabled null 忽略该条件
     * @return
     */
    List<Func> getByTypes(List<String> typeCode,Boolean isDisabled);

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
}
