package grape.base.rest.role;

import grape.base.rest.role.mvc.RoleController;
import grape.common.service.loginuser.LoginUser;
import grape.common.service.common.dataconstraint.ConstraintCompiledContent;
import grape.common.service.common.dataconstraint.IDataConstraintParseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/12/20 11:55
 */
@Component
public class RoleDataConstraintParseServiceImpl implements IDataConstraintParseService<LoginUser> {
    @Override
    public boolean support(String dataObject) {
        return isEqual(dataObject, RoleController.defaultDataObjectCode.dataObjectCode());
    }

    @Override
    public List<ConstraintCompiledContent> parseConstraint(String dataObjectCode, LoginUser loginUser) {
        List<ConstraintCompiledContent> constraintContents = new ArrayList<>();
        if (loginUser.superAdmin()) {
            constraintContents.add(new ConstraintCompiledContent("true"));
        }else{
            if (!isEmpty(loginUser.rawDataConstraints())) {
                roleInsql(dataObjectCode, loginUser, constraintContents);
            }else {
                constraintContents.add(new ConstraintCompiledContent("false"));
            }
        }
        return constraintContents;
    }

    /**
     * 角色分配兼容数据范围约束
     * @param dataObjectCode
     * @param loginUser
     * @param constraintContents
     */
    private void roleInsql(String dataObjectCode, LoginUser loginUser,List<ConstraintCompiledContent> constraintContents){
        if (isEqual(dataObjectCode, RoleController.defaultDataObjectCode.dataObjectCode())) {
            if (!isEmpty(loginUser.getAuthorities())) {
                Set<String> collect = loginUser.getAuthorities().stream()
                        .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith("ROLE_"))
                        .map(grantedAuthority -> grantedAuthority.getAuthority().replaceFirst("ROLE_","")).collect(Collectors.toSet());
                constraintContents.add(insql(collect,customeSqlTemplate.replace("id in","code in")));
            }
        }
    }
}
