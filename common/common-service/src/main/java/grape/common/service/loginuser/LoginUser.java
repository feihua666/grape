package grape.common.service.loginuser;

import grape.common.service.common.dataconstraint.RawDataConstraint;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * 当前登录用户
 * Created by yangwei
 * Created at 2020/1/15 14:10
 */
@Data
public class LoginUser extends User implements GrapeUserDetails {

    private List<RawDataConstraint> rawDataConstraints;
    // 用来存放更多详细信息
    private Object details;

    private boolean superAdmin;

    private String userId;

    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
    public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities,List<RawDataConstraint> rawDataConstraints) {
        super(username, password, authorities);
        this.rawDataConstraints = rawDataConstraints;
    }
    public LoginUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,List<RawDataConstraint> rawDataConstraints) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.rawDataConstraints = rawDataConstraints;
    }
    @Override
    public List<RawDataConstraint> rawDataConstraints() {
        return rawDataConstraints;
    }

    @Override
    public boolean superAdmin() {
        return superAdmin;
    }

    @Override
    public String userId() {
        return userId;
    }
}
