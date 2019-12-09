package grape.base.rest.postdatascoperel.mapper;

import grape.base.rest.postdatascoperel.vo.PostDataScopeRelVo;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
/**
 * <p>
 * 岗位数据范围关系表，多对多 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDataScopeRelWebMapper extends WebMapper<PostDataScopeRelVo, PostDataScopeRel> {

}
