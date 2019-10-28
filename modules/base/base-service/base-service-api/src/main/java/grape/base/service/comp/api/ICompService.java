package grape.base.service.comp.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.comp.po.Comp;
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
public interface ICompService extends IBaseTreeService<Comp> {



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
}
