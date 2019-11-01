package grape.base.service.area.mapper;

import grape.base.service.area.po.Area;
import grape.common.service.IBaseMapper;import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface AreaMapper extends IBaseMapper<Area> {

    List<Map<String, Object>> selectCopys(@Param("id")String id, @Param("parentId") String parentId);
}
