package grape.common.service.common;

import grape.common.AbstractLoginUser;
import grape.common.tools.ToolService;

import java.util.List;

/**
 * 该接口只是一个标识是否是默认的解析实现
 * @param <user>
 */
public interface IDataConstraintParseDefaultService<user extends AbstractLoginUser> extends IDataConstraintParseService<user> {

}
