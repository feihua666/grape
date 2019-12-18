package grape.base.service.userpost.impl;

import grape.base.service.comp.api.ICompService;
import grape.base.service.dept.api.IDeptService;
import grape.base.service.job.api.IJobService;
import grape.base.service.joblevel.api.IJobLevelService;
import grape.base.service.post.api.IPostService;
import grape.base.service.role.api.IRoleService;
import grape.base.service.role.po.Role;
import grape.base.service.userpost.api.IUserPostService;
import grape.base.service.userpost.dto.UserPostInfo;
import grape.base.service.userpost.mapper.UserPostMapper;
import grape.base.service.userpost.po.UserPost;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户岗位关系表，多对多，如果一个用户存在有效的岗位，即表示在职 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-09-26
 */
@Service
public class UserPostServiceImpl extends BaseServiceImpl<UserPostMapper, UserPost> implements IUserPostService {

    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private ICompService iCompService;
    @Autowired
    private IPostService iPostService;
    @Autowired
    private IJobLevelService iJobLevelService;
    @Autowired
    private IJobService iJobService;
    @Autowired
    private IRoleService iRoleService;
    @Override
    public UserPostInfo getUserPostInfo(UserPost userPost) {

        if (userPost == null) {
            return null;
        }
        UserPostInfo userPostInfo = new UserPostInfo();
        userPostInfo.setComp(iCompService.getById(userPost.getCompId()));
        userPostInfo.setDept(iDeptService.getById(userPost.getDeptId()));
        userPostInfo.setPost(iPostService.getById(userPost.getPostId()));
        userPostInfo.setJob(iJobService.getById(userPost.getJobId()));
        userPostInfo.setJobLevel(iJobLevelService.getById(userPost.getJobLevelId()));
        userPostInfo.setRoles(iRoleService.getByUserPostId(userPost.getId(),new Role().setIsDisabled(false)));
        return userPostInfo;
    }

    @Override
    public List<UserPostInfo> getUserPostInfos(List<UserPost> userPosts) {
        if (isEmpty(userPosts)) {
            return null;
        }
        List<UserPostInfo> r = new ArrayList<>(userPosts.size());
        for (UserPost userPost : userPosts) {
            r.add(getUserPostInfo(userPost));
        }
        return r;
    }
}
