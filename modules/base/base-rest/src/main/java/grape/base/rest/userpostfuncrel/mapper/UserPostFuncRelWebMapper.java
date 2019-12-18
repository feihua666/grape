package grape.base.rest.userpostfuncrel.mapper;

import grape.base.rest.userpostfuncrel.vo.UserPostFuncRelVo;
import grape.base.service.userpostfuncrel.po.UserPostFuncRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 用户岗位功能关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPostFuncRelWebMapper extends WebMapper<UserPostFuncRelVo, UserPostFuncRel> {

}
