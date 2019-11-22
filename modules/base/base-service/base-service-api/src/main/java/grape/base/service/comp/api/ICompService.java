package grape.base.service.comp.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.comp.po.Comp;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseTreeService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface ICompService extends IBaseTreeService<Comp>, ITransService<String,String> {

    public static final String trans_compName = "compName";
    public static final String trans_compCode = "compCode";
    public static final String trans_compParentName = "compParentName";

    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Comp comp = new Comp();
        comp.setCode(code);
        int r = count(Wrappers.query(comp));
        return r > 0;
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_compName, trans_compParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_compParentName)) {
            Comp parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else if (isEqual(type,trans_compName)) {
            Comp po = getById(key);
            if (po != null) {
                return po.getName();
            }
        }else if (isEqual(type,trans_compCode)) {
            Comp po = getById(key);
            if (po != null) {
                return po.getCode();
            }
        }
        return null;
    }
}
