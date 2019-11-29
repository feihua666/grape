package grape.common.rest.validation.form;

import grape.common.tools.ToolService;

import javax.validation.Valid;

/**
 * 需要验证的表单需实现该接口
 * Created by yangwei
 * Created at 2019/11/28 9:02
 */
public interface IFormValid  extends ToolService {

    public void valid(ValidResult result, ValidContext context);
}
