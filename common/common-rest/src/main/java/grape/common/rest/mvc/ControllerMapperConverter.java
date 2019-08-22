package grape.common.rest.mvc;

import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.vo.BaseVo;
import grape.common.service.IBaseService;
import grape.common.service.po.NormalBasePo;

/**
 * Created by yangwei
 * Created at 2019/8/21 21:17
 */
public interface ControllerMapperConverter<Vo extends BaseVo,Po extends NormalBasePo<Po>,CreateForm extends BaseForm, UpdateForm extends BaseForm, ListForm extends BasePageForm> {
    Po createFormToPo(CreateForm cf);
    Vo poToVo(Po po);
    Po updateFormToPo(UpdateForm up);
    Po listPageFormToPo(ListForm lf);
}
