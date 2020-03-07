package grape.common.rest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yangwei
 * Created at 2020/1/15 17:22
 */
public class UserDetailsPlaceholderServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        throw new RuntimeException("你调用了一个占位实现，请自己实现，并注入到spring上下文中，bean的名字参见：grape.common.rest.CommonRestSecurityConfig.userDetailsService 的注释 @Qualifier");
    }
}
