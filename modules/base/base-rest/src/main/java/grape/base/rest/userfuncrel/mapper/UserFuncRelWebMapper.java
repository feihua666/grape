package grape.base.rest.userfuncrel.mapper;

import grape.base.rest.userfuncrel.vo.UserFuncRelVo;
import grape.base.service.userfuncrel.po.UserFuncRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 用户功能关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserFuncRelWebMapper extends WebMapper<UserFuncRelVo, UserFuncRel> {

}
