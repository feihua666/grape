package grape.base.service.orgname.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.orgname.po.OrgName;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 组织树名称表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-20
 */
public interface IOrgNameService extends IBaseService<OrgName>, ITransService<String,String> {
    public static final String trans_orgNameName = "orgNameName";
    public static final String trans_orgNameCode = "orgNameCode";

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        assertParamNotEmpty(code,"code 不能为空");
        OrgName comp = new OrgName();
        comp.setCode(code);
        int r = count(Wrappers.query(comp));
        return r > 0;
    }

    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_orgNameName, trans_orgNameCode);
    }

    @Override
    default String trans(String type, String key){
        OrgName po = getById(key);
        if (po != null) {
            if (isEqual(type, trans_orgNameName)) {
                return po.getName();

            }else if (isEqual(type, trans_orgNameCode)) {
                return po.getCode();
            }
        }
        return null;
    }
}
