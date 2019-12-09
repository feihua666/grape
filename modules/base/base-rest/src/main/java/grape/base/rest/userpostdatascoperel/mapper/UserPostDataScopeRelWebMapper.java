package grape.base.rest.userpostdatascoperel.mapper;

import grape.base.rest.userpostdatascoperel.vo.UserPostDataScopeRelVo;
import grape.base.service.userpostdatascoperel.po.UserPostDataScopeRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 用户岗位数据范围关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPostDataScopeRelWebMapper extends WebMapper<UserPostDataScopeRelVo, UserPostDataScopeRel> {

}
