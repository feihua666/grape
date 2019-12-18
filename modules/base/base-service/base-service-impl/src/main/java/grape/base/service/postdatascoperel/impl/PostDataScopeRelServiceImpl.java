package grape.base.service.postdatascoperel.impl;

import grape.base.service.dataconstraint.api.IDataScopeService;
import grape.base.service.dataconstraint.mapper.DataScopeMapper;
import grape.base.service.dataconstraint.po.DataScope;
import grape.base.service.postdatascoperel.po.PostDataScopeRel;
import grape.base.service.postdatascoperel.mapper.PostDataScopeRelMapper;
import grape.base.service.postdatascoperel.api.IPostDataScopeRelService;
import grape.common.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 岗位数据范围关系表，多对多 服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2019-12-03
 */
@Service
public class PostDataScopeRelServiceImpl extends BaseServiceImpl<PostDataScopeRelMapper, PostDataScopeRel> implements IPostDataScopeRelService {

    @Autowired
    private DataScopeMapper dataScopeMapper;

    @Override
    public void doBind(String mainId, List<String> checkedIds, boolean isPostMain) {
        if (!isEmpty(checkedIds)) {
            // 插入数据对象id
            Map<String, String> dataObjectMap = new HashMap<>();
            if (isPostMain) {
                List<DataScope> dataScopes = dataScopeMapper.selectBatchIds(checkedIds);
                if (!isEmpty(dataScopes)) {
                    for (DataScope dataScope : dataScopes) {
                        dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
                    }
                }
            }else {
                DataScope dataScope = dataScopeMapper.selectById(mainId);
                dataObjectMap.put(dataScope.getId(),dataScope.getDataObjectId());
            }
            List<PostDataScopeRel> insert = new ArrayList<>(checkedIds.size());
            PostDataScopeRel postDataScopeRel = null;
            for (String checkedId : checkedIds) {
                postDataScopeRel = new PostDataScopeRel();
                if (isPostMain) {
                    postDataScopeRel.setPostId(mainId);
                    postDataScopeRel.setDataScopeId(checkedId);
                    postDataScopeRel.setDataObjectId(dataObjectMap.get(checkedId));
                }else {
                    postDataScopeRel.setPostId(checkedId);
                    postDataScopeRel.setDataScopeId(mainId);
                    postDataScopeRel.setDataObjectId(dataObjectMap.get(mainId));
                }
                insert.add(postDataScopeRel);
            }
            saveBatch(insert);
        }
    }
}
