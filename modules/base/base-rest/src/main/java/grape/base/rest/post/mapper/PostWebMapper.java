package grape.base.rest.post.mapper;

import grape.base.service.post.po.Post;
import grape.common.rest.mvc.WebMapper;
import org.mapstruct.Mapper;
import grape.base.rest.post.form.PostCreateForm;
import grape.base.rest.post.form.PostUpdateForm;
import grape.base.rest.post.form.PostListPageForm;
import grape.base.rest.post.vo.PostVo;
/**
 * <p>
 * 岗位表 前端领域模型映射
 * </p>
 *
 * @author yangwei
 * @since 2019-10-22
 */
@Mapper(componentModel = "spring")
public interface PostWebMapper extends WebMapper<PostVo, Post> {

    Post formToPo(PostCreateForm f);
    Post formToPo(PostUpdateForm f);
    Post formToPo(PostListPageForm f);
}
