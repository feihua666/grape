package grape.base.service.org.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.org.po.Org;
import grape.base.service.org.po.Org;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 组织树表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
public interface IOrgService extends IBaseTreeService<Org> , ITransService<String,String> {
    public static final String trans_orgName = "orgName";
    public static final String trans_orgParentName = "orgParentName";


    /**
     * 根据组织树名称id查询数量
     * @param orgNameId
     * @param query
     * @return
     */
    default public int countByOrgNameId(String orgNameId,Org query){
        assertParamNotEmpty(orgNameId,"orgNameId 不能为空");
        int r = count(Wrappers.query(query).lambda().eq(Org::getOrgNameId,orgNameId));
        return r;
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_orgName, trans_orgParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_orgParentName)) {
            Org parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else{
            Org po = getById(key);
            if (po != null) {
                if (isEqual(type,trans_orgName)) {
                    return po.getName();
                }
            }
        }
        return null;
    }
}
