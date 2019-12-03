package grape.base.service.user.map;

import grape.base.service.user.dto.UserCreateParam;
import grape.base.service.user.po.User;
import grape.common.service.pojomapper.ServiceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by yangwei
 * Created at 2019/11/28 14:32
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserServiceMapper extends ServiceMapper<User> {

    User paramToPo(UserCreateParam param);
}
