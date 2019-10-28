package ${cfg.mapperWebPackage};

import ${package.Entity}.${entity};
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import ${cfg.formPackage}.${entity}CreateForm;
import ${cfg.formPackage}.${entity}UpdateForm;
import ${cfg.formPackage}.${entity}ListPageForm;
import ${cfg.voPackage}.${entity}Vo;
/**
 * <p>
 * ${table.comment!} 前端领域模型映射
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring")
public interface ${entity}WebMapper extends WebMapper<${entity}Vo, ${entity}> {

    ${entity} formToPo(${entity}CreateForm f);
    ${entity} formToPo(${entity}UpdateForm f);
    ${entity} formToPo(${entity}ListPageForm f);
}
