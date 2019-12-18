package grape.base.rest.setting.dataconstraint;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import grape.base.service.BaseLoginUser;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.base.service.dataconstraint.dto.DataObjectAndScopeDto;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.service.common.ConstraintContent;
import grape.common.service.common.IDataConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据约束优先级：用户分配的数据范围>用户使用角色分配的数据范围>用户岗位分配的数据范围>岗位分配的数据范围
 * Created by yangwei
 * Created at 2019/12/3 17:15
 */
@Component
public class BaseDataConstraintServiceImpl implements IDataConstraintService<BaseLoginUser> {
    @Autowired
    private IDataScopeCustomRelService iDataScopeCustomRelService;
    private TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());

    @Override
    public ConstraintContent parseConstraint(String dataObjectCode, BaseLoginUser loginUser) {
        if (loginUser.getIsSuperAdmin()) {
            // 超级管理员没有约束
            return new ConstraintContent("");
        }else {

            if(!isEmpty(loginUser.getDataObjectAndScopes())){
                DataObjectAndScopeDto first = loginUser.getDataObjectAndScopes().stream().filter(item -> isEqual(item.getDataObject().getCode(), dataObjectCode)).findFirst().get();

                DataScope dataScope = first.getDataScope();
                if (dataScope.getIsCustom()) {
                    List<DataScopeCustomRel> customRels = iDataScopeCustomRelService.getByDataScopeId(dataScope.getId());
                    List<String> dataIds = customRels.stream().map(item -> item.getDataId()).collect(Collectors.toList());
                    String insql = dataIds.stream().map(str -> "'" + str + "'").collect(Collectors.joining(","));
                    return new ConstraintContent(dataScope.getConstraintContent().replace(IDataConstraintService.insqlReplace,insql));
                }else {
                    // 解析内容
                    // 支持freeMarker
                    Template template = engine.getTemplate(dataScope.getConstraintContent());
                    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(loginUser);
                    System.out.println(stringObjectMap);
                    String compiledSql = template.render(stringObjectMap);
                    return new ConstraintContent(compiledSql);
                }
            }
        }
        return null;
    }
}
