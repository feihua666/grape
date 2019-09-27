package grape.base.service.func.mapper;

import grape.base.service.func.po.Func;
import grape.common.service.IBaseMapper;

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

    List<Func> getByRoleId(Long roleId);
}