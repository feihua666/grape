package grape.base.service.area.api;

import grape.base.service.area.po.Area;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-23
 */
public interface IAreaService extends IBaseTreeService<Area> , ITransService<String,String> {

    public static final String trans_areaName = "areaName";
    public static final String trans_areaParentName = "areaParentName";

    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_areaName, trans_areaParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_areaParentName)) {
            Area parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else if (isEqual(type,trans_areaName)) {

            Area area = getById(key);
            if (area != null) {
                return area.getName();
            }
        }
        return null;
    }
}
