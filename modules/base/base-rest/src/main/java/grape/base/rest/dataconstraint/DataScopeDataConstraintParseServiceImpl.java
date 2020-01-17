package grape.base.rest.dataconstraint;

import grape.base.rest.dataconstraint.mvc.DataScopeController;
import grape.common.service.common.dataconstraint.ConstraintCompiledContent;
import grape.common.service.common.dataconstraint.IDataConstraintParseService;
import grape.common.service.common.dataconstraint.RawDataConstraint;
import grape.common.service.loginuser.GrapeUserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yangwei
 * Created at 2019/12/20 11:55
 */
@Component
public class DataScopeDataConstraintParseServiceImpl implements IDataConstraintParseService<GrapeUserDetails> {
    @Override
    public boolean support(String dataObject) {
        return isEqual(dataObject, DataScopeController.defaultDataObjectCode.dataObjectCode());
    }

    @Override
    public List<ConstraintCompiledContent> parseConstraint(String dataObjectCode, GrapeUserDetails loginUser) {
        List<ConstraintCompiledContent> constraintContents = new ArrayList<>();
        if (loginUser.superAdmin()) {
            constraintContents.add(new ConstraintCompiledContent("true"));
        }else{
            if (!isEmpty(loginUser.rawDataConstraints())) {
                dataScopeInsql(dataObjectCode, loginUser, constraintContents);
            }else {
                constraintContents.add(new ConstraintCompiledContent("false"));
            }
        }
        return constraintContents;
    }

    /**
     * 数据范围约束分配兼容数据范围约束
     * @param dataObjectCode
     * @param loginUser
     * @param constraintContents
     */
    private void dataScopeInsql(String dataObjectCode, GrapeUserDetails loginUser,List<ConstraintCompiledContent> constraintContents){
        if (isEqual(dataObjectCode, DataScopeController.defaultDataObjectCode.dataObjectCode())) {
            if (!isEmpty(loginUser.rawDataConstraints())) {
                Set<String> ids = new HashSet<>();

                for (RawDataConstraint dataObjectAndScope : loginUser.rawDataConstraints()) {
                    Set<String> collect = dataObjectAndScope.getDataScopes().stream().map(dataScope -> dataScope.id()).collect(Collectors.toSet());
                    ids.addAll(collect);
                }

                constraintContents.add(insql(ids,null));
            }
        }
    }
}
