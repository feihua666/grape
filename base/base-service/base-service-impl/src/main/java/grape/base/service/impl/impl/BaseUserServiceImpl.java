package grape.base.service.impl.impl;

import grape.base.service.impl.mapper.BaseUserMapper;
import grape.base.service.api.service.IBaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理系统用户表 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-07-23
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {

}
