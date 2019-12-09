package grape.base.rest.setting.dataconstraint;

import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.po.DataObject;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据约束优先级：用户分配的数据范围>用户使用角色分配的数据范围>用户岗位分配的数据范围>岗位分配的数据范围
 * Created by yangwei
 * Created at 2019/12/3 17:15
 */
@Component
public class BaseDataConstraintServiceImpl implements IDataConstraintService<BaseLoginUser> {

    @Autowired
    private IDataObjectService iDataObjectService;
    @Override
    public ConstraintContent parseConstraint(String dataObjectCode, BaseLoginUser loginUser) {
        DataObject dataObject = iDataObjectService.getByCode(dataObjectCode);

        return null;
    }
}
