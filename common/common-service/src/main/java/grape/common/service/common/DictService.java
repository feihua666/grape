package grape.common.service.common;

/**
 * 字典服务，这是一个全局的字典服务
 * 主要是角色在使用字典服务时不显示依赖
 * 暂时主要由来是在表单验证时根据字典id换编码
 * Created by yangwei
 * Created at 2019/11/27 14:30
 */
public interface DictService {

    String getCodeById(String id);
}
