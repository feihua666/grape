package grape.base.rest.userpost.mapper;

import grape.base.service.userpost.po.UserPost;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.userpost.form.UserPostCreateForm;
import grape.base.rest.userpost.form.UserPostUpdateForm;
import grape.base.rest.userpost.form.UserPostListPageForm;
import grape.base.rest.userpost.vo.UserPostVo;
/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Mapper(componentModel = "spring")
public interface UserPostWebMapper extends WebMapper<UserPostVo, UserPost, UserPostCreateForm,UserPostUpdateForm,UserPostListPageForm> {

}
