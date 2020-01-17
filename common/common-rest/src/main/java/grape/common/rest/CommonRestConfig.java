package grape.common.rest;

import grape.common.rest.common.GlobalInterceptor;
import grape.common.rest.security.UserDetailsPlaceholderServiceImpl;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * Created by yangwei
 * Created at 2019/7/27 13:44
 */
@EnableFeignClients
@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class CommonRestConfig implements WebMvcConfigurer {


    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public GlobalInterceptor globalInterceptor(){
        return new GlobalInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor()).addPathPatterns("/**");
    }
    // 使用快速失败模式,好像加了并没有什么用，还是全部验证
    // 总结一下：下拉添加下面的validator()这里里没有作用，但把这个加个主启动项目配置类中会有作用，但这样就比较分散了，不是我的初衷
    // 所以再尝试又重写了下面的getValidator()后生效了
/*    @Bean
    @Primary
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation
                .byProvider( HibernateValidator.class )
                .configure()
                .failFast(true)
                //.addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
    // 加 了这个，校验器注入不了spring对象
    @Override
    public org.springframework.validation.Validator getValidator() {
        return new SpringValidatorAdapter(validator());
    }*/


    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
        config.addAllowedOrigin("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
