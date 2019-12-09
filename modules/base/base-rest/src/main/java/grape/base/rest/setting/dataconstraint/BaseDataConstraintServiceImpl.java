package grape.base.rest.setting.dataconstraint;

import cn.hutool.extra.template.engine.freemarker.FreemarkerTemplate;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataObjectService;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.base.service.dataconstraint.dto.DataConstraintDto;
import grape.base.service.dataconstraint.po.DataObject;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 数据约束优先级：用户分配的数据范围>用户使用角色分配的数据范围>用户岗位分配的数据范围>岗位分配的数据范围
 * Created by yangwei
 * Created at 2019/12/3 17:15
 */
@Component
public class BaseDataConstraintServiceImpl implements IDataConstraintService<BaseLoginUser> {

    @Autowired
    private IDataObjectService iDataObjectService;
    @Autowired
    private IDataScopeCustomRelService iDataScopeCustomRelService;
    @Override
    public ConstraintContent parseConstraint(String dataObjectCode, BaseLoginUser loginUser) {
        if (loginUser.getIsSuperAdmin()) {
            // 超级管理员没有约束
            return new ConstraintContent("");
        }else {

            if(!isListEmpty(loginUser.getCurrentDataConstraints())){
                DataConstraintDto first = loginUser.getCurrentDataConstraints().stream().filter(item -> isEqual(item.getDataObject().getCode(), dataObjectCode)).findFirst().get();

                DataScope dataScope = first.getDataScope();
                if (dataScope.getIsCustom()) {
                    List<DataScopeCustomRel> customRels = iDataScopeCustomRelService.getByDataScopeId(dataScope.getId());
                    List<String> dataIds = customRels.stream().map(item -> item.getDataId()).collect(Collectors.toList());
                    String insql = dataIds.stream().map(str -> "'" + str + "'").collect(Collectors.joining(","));
                    return new ConstraintContent(dataScope.getConstraintContent().replace(IDataConstraintService.insqlReplace,insql));
                }else {
                    return new ConstraintContent(dataScope.getConstraintContent());
                }
            }
        }
        return null;
    }
}
