package grape.base.service.job.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.job.po.Job;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 职务表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-29
 */
public interface IJobService extends IBaseService<Job> , ITransService<String,String> {

    public static final String trans_type_jobName = "jobName";
    public static final String trans_type_jobCode = "jobCode";

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

    @Override
    default boolean support(String type){
        return isEqualAny(type,trans_type_jobName,trans_type_jobCode);
    }

    @Override
    default String trans(String type, String key){
        Job job = getById(key);
        if (job != null) {
            if(isEqual(type,trans_type_jobName)){
                return job.getName();
            }else if(isEqual(type,trans_type_jobCode)){
                return job.getCode();
            }
        }
        return null;
    }
}
