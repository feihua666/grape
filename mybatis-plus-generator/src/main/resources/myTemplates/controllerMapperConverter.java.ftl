package grape.base.rest.mvc;

import ${package.Entity}.${entity};
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;

/**
 * <p>
 * ${table.comment!} 前端领域模型映射
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring")
public interface ${entity}ControllerMapper extends ControllerMapperConverter<${entity}Vo, ${entity}, ${entity}CreateForm,${entity}UpdateForm,${entity}ListPageForm> {

}
