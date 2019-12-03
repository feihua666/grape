package grape.common.service.common;

import java.io.Serializable;

/**
 * 定义一个数据对象接口，该接口可以对象数据库中的编码
 * 用来标识是否为数据对象
 * Created by yangwei
 * Created at 2019/12/3 13:31
 */
public interface IDataObject<T> extends Serializable {
    /**
     * 获取数据对象编码
     * @return
     */
    public String dataObjectCode();
}
