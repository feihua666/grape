package grape.base.service.dept.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dept.po.Dept;
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
public interface IDeptService extends IBaseTreeService<Dept> , ITransService<String,String> {
    public static final String trans_deptName = "deptName";
    public static final String trans_deptCode = "deptCode";
    public static final String trans_deptParentName = "deptParentName";


    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Dept dept = new Dept();
        dept.setCode(code);
        int r = count(Wrappers.query(dept));
        return r > 0;
    }
    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_deptCode, trans_deptName, trans_deptParentName);
    }

    @Override
    default String trans(String type, String key){
        if (isEqual(type,trans_deptParentName)) {
            Dept parent = getParent(key);
            if (parent != null) {
                return parent.getName();
            }
        }else{
            Dept po = getById(key);
            if (po != null) {
                if (isEqual(type,trans_deptName)) {
                    return po.getName();
                }else if (isEqual(type,trans_deptCode)) {
                    return po.getCode();
                }
            }
        }
        return null;
    }
}
