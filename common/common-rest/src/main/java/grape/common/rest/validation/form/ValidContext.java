package grape.common.rest.validation.form;

import lombok.Data;

import java.util.Map;

/**
 * 验证时的上下文，可以提供一些额外的属性
 * 暂时只存放了字典与编码对应值map
 *
 * Created by yangwei
 * Created at 2019/11/28 9:07
 */
@Data
public class ValidContext {
    private Map<String,String> dictIdCode;
}
