package grape.base.service.dept.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.dept.po.Dept;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.IBaseTreeService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
public interface IDeptService extends IBaseTreeService<Dept> {


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
}
