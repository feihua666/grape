package grape.base.rest.mvc;

import grape.base.service.api.po.BaseAreaPo;
import grape.common.rest.mvc.ControllerMapperConverter;
import org.mapstruct.Mapper;

/**
 * Created by yangwei
 * Created at 2019/8/21 20:53
 */
@Mapper(componentModel = "spring")
public interface BaseAreaConMapper extends ControllerMapperConverter<BaseAreaPoVo, BaseAreaPo, BaseAreaPoCreateForm,BaseAreaPoUpdateForm,BaseAreaPoListPageForm> {

}
