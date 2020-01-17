package grape.base.rest.setting.dataconstraint;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import grape.base.service.dataconstraint.api.IDataScopeCustomRelService;
import grape.base.service.dataconstraint.po.DataScopeCustomRel;
import grape.common.service.common.dataconstraint.*;
import grape.common.service.loginuser.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 默认的数据约束解析数据约束
 * Created by yangwei
 * Created at 2019/12/3 17:15
 */
@Component
public class BaseDataConstraintServiceImpl implements IDataConstraintParseDefaultService<LoginUser> {
    @Autowired
    private IDataScopeCustomRelService iDataScopeCustomRelService;
    private TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
    @Override
    public boolean support(String dataObject) {
        return false;
    }

    @Override
    public List<ConstraintCompiledContent> parseConstraint(String dataObjectCode, LoginUser loginUser) {

        List<ConstraintCompiledContent> constraintContents = new ArrayList<>();

        if (loginUser == null) {
            // 没有数据
            constraintContents.add(new ConstraintCompiledContent("false"));
            return constraintContents;
        }

        if (loginUser.superAdmin()) {
            // 超级管理员没有约束
            constraintContents.add(new ConstraintCompiledContent("true"));
            return constraintContents;
        }
        if(!isEmpty(loginUser.rawDataConstraints())){
            RawDataConstraint first = loginUser.rawDataConstraints().stream().filter(item -> isEqual(item.getDataObject().dataObjectCode(), dataObjectCode)).findFirst().get();

            List<DefaultDataScope> dataScopes = first.getDataScopes();
            for (DefaultDataScope dataScope : dataScopes) {
                if (dataScope.isCustom()) {
                    List<DataScopeCustomRel> customRels = iDataScopeCustomRelService.getByDataScopeId(dataScope.id());
                    Set<String> dataIds = customRels.stream().map(item -> item.getDataId()).collect(Collectors.toSet());

                    constraintContents.add(insql(dataIds,dataScope.constraintContent()) );
                }else {
                    // 解析内容
                    // 支持enjoy模板
                    Template template = engine.getTemplate(dataScope.constraintContent());
                    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(loginUser);
                    String compiledSql = template.render(stringObjectMap);
                    constraintContents.add( new ConstraintCompiledContent(compiledSql));
                }
            }
            return constraintContents;

        }
        // 没有数据
        constraintContents.add(new ConstraintCompiledContent("false"));

        return constraintContents;
    }




}
