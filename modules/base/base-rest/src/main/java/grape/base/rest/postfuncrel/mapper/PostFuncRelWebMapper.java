package grape.base.rest.postfuncrel.mapper;

import grape.base.rest.postfuncrel.vo.PostFuncRelVo;
import grape.base.service.postfuncrel.po.PostFuncRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 岗位功能关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostFuncRelWebMapper extends WebMapper<PostFuncRelVo, PostFuncRel> {

}
