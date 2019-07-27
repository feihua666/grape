package grape.common.rest.form;

import lombok.Data;

/**
 * 带有分页的表单
 * Created by yangwei
 * Created at 2019/7/27 16:25
 */
@Data
public class BasePageForm extends BaseForm {
    /**
     * 请求页码
     */
    private long current = 1;
    /**
     * 每页条数
     */
    private long size = 10;

}
