package grape.common.service.loginuser;

import grape.common.service.common.dataconstraint.RawDataConstraint;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 扩展用户接口
 * Created by yangwei
 * Created at 2020/1/15 13:47
 */
public interface GrapeUserDetails extends UserDetails {
    /**
     * 数据约束范围
     * @return
     */
    List<RawDataConstraint> rawDataConstraints();

    // 是否超级管理员
    public boolean superAdmin();

    public String userId();
}
