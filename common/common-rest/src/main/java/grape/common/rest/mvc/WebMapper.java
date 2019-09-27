package grape.common.rest.mvc;

import grape.common.rest.form.BaseForm;
import grape.common.rest.form.BasePageForm;
import grape.common.rest.vo.BaseVo;
import grape.common.service.IBaseService;
import grape.common.service.po.IDBasePo;
import grape.common.service.po.NormalBasePo;

/**
 * Created by yangwei
 * Created at 2019/8/21 21:17
 */
public interface WebMapper<Vo extends BaseVo,Po extends IDBasePo<Long,Po>,CreateForm extends BaseForm, UpdateForm extends BaseForm, ListForm extends BasePageForm> {
    /**
     * 单表创建表单转po
     * @param cf
     * @return
     */
    Po createFormToPo(CreateForm cf);

    /**
     * 单表po转vo
     * @param po
     * @return
     */
    Vo poToVo(Po po);

    /**
     * 单表更新表单转po
     * @param up
     * @return
     */
    Po updateFormToPo(UpdateForm up);

    /**
     * 单表列表查询表单转po
     * @param lf
     * @return
     */
    Po listPageFormToPo(ListForm lf);
}
