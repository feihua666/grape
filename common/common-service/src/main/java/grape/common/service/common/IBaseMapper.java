package grape.common.service.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 所有mapper的基类,如果有扩展接口方便扩展
 * Created by yangwei
 * Created at 2019/7/24 17:56
 */
public interface IBaseMapper<Po> extends BaseMapper<Po> {
}