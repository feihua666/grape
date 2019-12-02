package grape.common.rest;

import grape.common.rest.common.GlobalInterceptor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.hibernate.validator.HibernateValidator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by yangwei
 * Created at 2019/7/27 13:44
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class CommonRestConfig implements WebMvcConfigurer {

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        return chainDefinition;
    }
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
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
    @Bean
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

    @Override
    public org.springframework.validation.Validator getValidator() {
        return new SpringValidatorAdapter(validator());
    }
}
