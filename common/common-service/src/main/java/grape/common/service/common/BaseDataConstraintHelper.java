package grape.common.service.common;

import grape.common.AbstractLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/12/20 11:29
 */
@Component
public class BaseDataConstraintHelper<user extends AbstractLoginUser> {
    @Autowired(required = false)
    private List<IDataConstraintParseService<user>> iDataConstraintServices;

    // 默认的调用
    @Autowired(required = false)
    private IDataConstraintParseDefaultService  baseDataConstraintService;
    
    public List<ConstraintContent> doParseConstraint(String dataObjectCode, user loginUser){
        for (IDataConstraintParseService<user> iDataConstraintService : iDataConstraintServices) {
            if (iDataConstraintService.support(dataObjectCode) && !(iDataConstraintService instanceof IDataConstraintParseDefaultService)) {
                return iDataConstraintService.parseConstraint(dataObjectCode, loginUser);
            }
        }
        return baseDataConstraintService.parseConstraint(dataObjectCode, loginUser);
    }
}
