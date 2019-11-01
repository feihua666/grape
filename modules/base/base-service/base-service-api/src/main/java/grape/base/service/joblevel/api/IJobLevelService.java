package grape.base.service.joblevel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.joblevel.po.JobLevel;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.IBaseService;

/**
 * <p>
 * 职务级别表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
public interface IJobLevelService extends IBaseService<JobLevel> {


    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        JobLevel jobLevel = new JobLevel();
        jobLevel.setCode(code);
        int r = count(Wrappers.query(jobLevel));
        return r > 0;
    }
}
