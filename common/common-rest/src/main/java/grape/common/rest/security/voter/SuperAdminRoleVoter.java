package grape.common.rest.security.voter;

import grape.common.AbstractLoginUser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 超级管理员投票器，如果是超级管理员角色则通过
 * Created by yangwei
 * Created at 2020/1/14 9:52
 */
public class SuperAdminRoleVoter extends RoleVoter {

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if(AbstractLoginUser.superadminCode.equals(authority.getAuthority()) || (("ROLE_" + AbstractLoginUser.superadminCode).equals(authority.getAuthority()))){
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_ABSTAIN;
    }
}
