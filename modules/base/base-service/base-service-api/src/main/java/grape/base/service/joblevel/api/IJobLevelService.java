package grape.base.service.joblevel.api;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import grape.base.service.joblevel.po.JobLevel;
import grape.common.exception.runtime.InvalidParamsException;
import grape.common.service.common.IBaseService;
import grape.common.service.trans.ITransService;

/**
 * <p>
 * 职务级别表 服务类
 * </p>
 *
 * @author yangwei
 * @since 2019-10-31
 */
public interface IJobLevelService extends IBaseService<JobLevel>, ITransService<String,String> {
    public static final String trans_jobLevelName = "jobLevelName";
    public static final String trans_jobLevelCode = "jobLevelCode";

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

    @Override
    default boolean support(String type){
        return isEqualAny(type, trans_jobLevelName, trans_jobLevelName);
    }

    @Override
    default String trans(String type, String key){
        JobLevel po = getById(key);
        if(po != null){
            if (isEqual(type,trans_jobLevelName)) {
                return po.getName();
            }else if (isEqual(type,trans_jobLevelCode)) {
                return po.getCode();
            }
        }
        return null;
    }
}
