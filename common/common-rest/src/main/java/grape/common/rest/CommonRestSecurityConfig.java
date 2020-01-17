package grape.common.rest;

import grape.common.rest.security.JwtAuthenticationProvider;
import grape.common.rest.security.UserDetailsPlaceholderServiceImpl;
import grape.common.rest.security.voter.SuperAdminRoleVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2020/1/13 14:19
 */
@Configuration
public class CommonRestSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("userDetailsService")
    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Qualifier("jwtUserDetailsService")
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用baisc和form认证，在Controller中自己实现认证逻辑
                .httpBasic().disable()
                .formLogin().disable()
                .anonymous().disable()
                .csrf().disable()
                .logout().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }
    @Override
    @Bean
    //注入authenticationManager
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = this.userDetailsService;
        if (userDetailsService == null) {
            userDetailsService = (new UserDetailsPlaceholderServiceImpl());
        }
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(jwtAuthenticationProvider());
    }

    @Bean
    @Order
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtUserDetailsService);
        return jwtAuthenticationProvider;
    }
}
