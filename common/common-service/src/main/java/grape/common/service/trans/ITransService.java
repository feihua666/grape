package grape.common.service.trans;

import grape.common.tools.ToolService;

/**
 * 提供翻译服务
 * 目前主要用于controller提供字典、机构等根据id翻译成名称
 * 该接口放在这意在也可以在纯service层提供翻译，但得加aop来处理相关逻辑
 * Created by yangwei
 * Created at 2019/10/9 9:24
 */
public interface ITransService<R, K> extends ToolService {
    /**
     * 是否支持
     * @param type 一个翻译的标识
     * @return
     */
    boolean support(String type);

    /**
     * 根据key翻译
     * @param type 支持的类型
     * @param key
     * @return
     */
    R trans(String type,K key);
}
