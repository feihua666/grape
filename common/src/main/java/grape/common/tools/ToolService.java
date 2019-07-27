package grape.common.tools;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 一个常用工具集合
 * 为了一致的编码思路，service实现类和controller继承该以实现方便的工具方法
 * 其它类也可以继承来达到一致的目的
 * Created by yangwei
 * Created at 2019/7/25 9:34
 */
public interface ToolService {
    /**
     * 比较字符串相同
     * @param cs1
     * @param cs2
     * @return
     */
    default boolean isEqual(final CharSequence cs1, final CharSequence cs2){
        return StringUtils.equals(cs1,cs2);
    }

    /**
     * 判断是否为空
     * @param list
     * @return true 如果是null或size为0
     */
    default boolean isListEmpty(List list){
        return list == null || list.isEmpty();
    }
}
