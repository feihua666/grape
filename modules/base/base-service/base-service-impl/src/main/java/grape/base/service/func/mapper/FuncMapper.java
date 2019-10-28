package grape.base.service.func.mapper;

import grape.base.service.func.po.Func;
import grape.common.service.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单功能表 Mapper 接口
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface FuncMapper extends IBaseMapper<Func> {

    /**
     * 根据角色id查询角色关联的未被禁用的功能
     *
     * @param roleId      必填
     * @param typeDictIds 选填 如果为null，忽略该条件
     * @param isDisabled 选填 如果为null，忽略该条件
     * @return
     */
    List<Func> getByRoleId(@Param("roleId") String roleId, @Param("typeDictIds") List<String> typeDictIds, @Param("isDisabled")Boolean isDisabled);

    /**
     * 根据类型查询
     * @param typeDictIds 字典ids
     * @param isDisabled 选填 如果为null，忽略该条件
     * @return
     */
    List<Func> getByType( @Param("typeDictIds") List<String> typeDictIds, @Param("isDisabled")Boolean isDisabled);

}
