package grape.base.service.job.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.job.po.Job;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;

/**
 * <p>
 * 职务表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
public interface IJobService extends IBaseService<Job> {


    /**
     * 判断编码是否已存在
     * @param code
     * @return
     */
    default boolean existCode(String code){
        if (isStrEmpty(code)) {
            throw new InvalidParamsException("code 不能为空");
        }
        Job job = new Job();
        job.setCode(code);
        int r = count(Wrappers.query(job));
        return r > 0;
    }
}
