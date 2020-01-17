package grape.common.rest.security.config;

import grape.common.rest.security.voter.SuperAdminRoleVoter;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 自定义注解扩展
 * Created by yangwei
 * Created at 2020/1/14 11:42
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfiguration extends org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration  {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        AbstractAccessDecisionManager accessDecisionManager = (AbstractAccessDecisionManager) super.accessDecisionManager();
        accessDecisionManager.getDecisionVoters().add(new SuperAdminRoleVoter());
        return accessDecisionManager;
    }
}
