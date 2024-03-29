package grape.common.service.common;

import grape.common.AbstractLoginUser;
import grape.common.tools.ToolService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据范围控制接口
 * 主要解决用户的数据权限
 * 本项目认为根本没有绝对的数据权限这种东西，用户的数据是根据业务来的；
 * 这里提供一个通用的接口，来处理额外的约束条件，如果有数据对象需要额外的约束需实现该接口
 * 一个数据对象表中的编码对应一种数据对象，比如：用户算一种数据对象，但往往在项目中一个实体可能代表一种数据对象，但这在数据库表中不好体现，只好对应一个编码
 * 也可以这么理解：一个数据对象编码可能对应一个实现，也可能不完全是，但又往往业务中的数据对象又有很多，具体业务具体分析吧，谁又说的清呢。
 * 所以一个数据对象编码是一种数据对象，不用关心具体实现
 * Created by yangwei
 * Created at 2019/11/25 16:08
 */
public interface IDataConstraintParseService<user extends AbstractLoginUser> extends ToolService {

    public static final String insqlReplace = "{{insql}}";
    public static final String customeSqlTemplate = "id in ("+ insqlReplace +")";

    /**
     * 可以自定义解析
     * @param dataObject
     * @return
     */
    default boolean support(IDataObject dataObject){
        return support(dataObject.dataObjectCode());
    }
    public boolean support(String dataObject);

    /**
     * 解析约束条件
     * @param dataObject
     * @param loginUser
     * @return
     */
    default public List<ConstraintContent> parseConstraint(IDataObject dataObject, user loginUser){
        return parseConstraint(dataObject.dataObjectCode(), loginUser);
    }
    /**
     * 解析约束条件
     * @param dataObjectCode
     * @param loginUser
     * @return
     */
    public List<ConstraintContent> parseConstraint(String dataObjectCode, user loginUser);


    /**
     * 组装insql内容片段
     * @param dataIds
     * @param constraintContent
     * @return
     */
    default public ConstraintContent insql(Set<String> dataIds, String constraintContent){
        String insql = dataIds.stream().map(str -> "'" + str + "'").collect(Collectors.joining(","));
        if (isStrEmpty(constraintContent)) {
            constraintContent = IDataConstraintParseService.customeSqlTemplate;
        }
        return new ConstraintContent(constraintContent.replace(IDataConstraintParseService.insqlReplace,insql));
    }
}
