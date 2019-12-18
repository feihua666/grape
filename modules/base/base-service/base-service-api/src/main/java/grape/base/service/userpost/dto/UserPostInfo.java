package grape.base.service.userpost.dto;

import grape.base.service.comp.po.Comp;
import grape.base.service.dept.po.Dept;
import grape.base.service.job.po.Job;
import grape.base.service.joblevel.po.JobLevel;
import grape.base.service.post.po.Post;
import grape.base.service.role.po.Role;
import grape.common.service.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/11/18 14:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPostInfo extends BaseDto {

    private Comp comp;
    private Dept dept;
    private Post post;
    private Job job;
    private JobLevel jobLevel;
    // 分配的角色
    private List<Role> roles;
}
