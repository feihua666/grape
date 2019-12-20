package grape.base.rest.func;

import grape.base.rest.func.mvc.FuncController;
import grape.base.rest.role.mvc.RoleController;
import grape.base.service.BaseLoginUser;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintParseService;
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
public class FuncDataConstraintParseServiceImpl implements IDataConstraintParseService<BaseLoginUser> {
    @Override
    public boolean support(String dataObject) {
        return isEqual(dataObject, FuncController.defaultDataObjectCode.dataObjectCode());
    }

    @Override
    public List<ConstraintContent> parseConstraint(String dataObjectCode, BaseLoginUser loginUser) {
        List<ConstraintContent> constraintContents = new ArrayList<>();
        if (loginUser.getIsSuperAdmin()) {
            constraintContents.add(new ConstraintContent("true"));
        }else{
            if (!isEmpty(loginUser.getDataObjectAndScopes())) {
                funcInsql(dataObjectCode, loginUser, constraintContents);
            }else {
                constraintContents.add(new ConstraintContent("false"));
            }
        }
        return constraintContents;
    }
    /**
     * 功能分配兼容数据范围约束
     * @param dataObjectCode
     * @param loginUser
     * @param constraintContents
     */
    private void funcInsql(String dataObjectCode, BaseLoginUser loginUser,List<ConstraintContent> constraintContents){
        if (isEqual(dataObjectCode, FuncController.defaultDataObjectCode.dataObjectCode())) {
            if (!isEmpty(loginUser.getFuncs())) {
                Set<String> collect = loginUser.getFuncs().stream().map(func -> func.getId()).collect(Collectors.toSet());
                constraintContents.add(insql(collect,null));
            }
        }
    }
}
