package grape.common.rest;

import grape.common.rest.security.UserDetailsPlaceholderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by yangwei
 * Created at 2020/1/13 14:19
 */
@Configuration
public class CommonRestSecurityConfig extends WebSecurityConfigurerAdapter {



    @Qualifier("userDetailsService")
    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用baisc和form认证
                .httpBasic().disable()
                //.formLogin().disable()
                .anonymous().disable()
                .csrf().disable()
                .logout().disable()
                .formLogin().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 注入authenticationManager
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = this.userDetailsService;
        if (userDetailsService == null) {
            userDetailsService = (userDetailsPlaceholderService());
        }
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 注入一个占位 userDetailService，因为如果依赖了common-rest项目可能不用这块东西
     * @return
     */
    @Bean
    public UserDetailsService userDetailsPlaceholderService(){
        return new UserDetailsPlaceholderServiceImpl();
    }

    /**
     * 密码加解密处理，用户添加的时候也会用到
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
