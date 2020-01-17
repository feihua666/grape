package grape.common.service.common.dataconstraint;

import grape.common.AbstractLoginUser;
import grape.common.service.loginuser.GrapeUserDetails;

/**
 * 该接口只是一个标识是否是默认的解析实现
 * @param <user>
 */
public interface IDataConstraintParseDefaultService<user extends GrapeUserDetails> extends IDataConstraintParseService<user> {

}
