package grape.base.rest.dataconstraint;

import grape.base.rest.dataconstraint.mvc.DataScopeController;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.dto.DataObjectAndScopeDto;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintParseService;
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
public class DataScopeDataConstraintParseServiceImpl implements IDataConstraintParseService<BaseLoginUser> {
    @Override
    public boolean support(String dataObject) {
        return isEqual(dataObject, DataScopeController.defaultDataObjectCode.dataObjectCode());
    }

    @Override
    public List<ConstraintContent> parseConstraint(String dataObjectCode, BaseLoginUser loginUser) {
        List<ConstraintContent> constraintContents = new ArrayList<>();
        if (loginUser.getIsSuperAdmin()) {
            constraintContents.add(new ConstraintContent("true"));
        }else{
            if (!isEmpty(loginUser.getDataObjectAndScopes())) {
                dataScopeInsql(dataObjectCode, loginUser, constraintContents);
            }else {
                constraintContents.add(new ConstraintContent("false"));
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
    private void dataScopeInsql(String dataObjectCode, BaseLoginUser loginUser,List<ConstraintContent> constraintContents){
        if (isEqual(dataObjectCode, DataScopeController.defaultDataObjectCode.dataObjectCode())) {
            if (!isEmpty(loginUser.getDataObjectAndScopes())) {
                Set<String> ids = new HashSet<>();

                for (DataObjectAndScopeDto dataObjectAndScope : loginUser.getDataObjectAndScopes()) {
                    Set<String> collect = dataObjectAndScope.getDataScopes().stream().map(dataScope -> dataScope.getId()).collect(Collectors.toSet());
                    ids.addAll(collect);
                }

                constraintContents.add(insql(ids,null));
            }
        }
    }
}
