package grape.base.rest.userdatascoperel.mapper;

import grape.base.rest.userdatascoperel.vo.UserDataScopeRelVo;
import grape.base.service.userdatascoperel.po.UserDataScopeRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 用户数据范围关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDataScopeRelWebMapper extends WebMapper<UserDataScopeRelVo, UserDataScopeRel> {

}
