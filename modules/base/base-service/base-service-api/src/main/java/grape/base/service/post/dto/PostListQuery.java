package grape.base.service.post.dto;

import grape.common.service.query.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yangwei
 * Created at 2019/11/20 12:52
 */
@Data
@EqualsAndHashCode(callSuper=false)

public class PostListQuery extends BaseQuery {

    private String code;

    private String name;

    private String deptId;

    private Boolean isIncludePublic = true;
}
