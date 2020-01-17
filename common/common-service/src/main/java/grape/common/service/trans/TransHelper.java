package grape.common.service.trans;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import grape.common.tools.RequestIdTool;
import grape.common.tools.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2019/11/25 10:35
 */
@Component
@Slf4j
public class TransHelper implements ToolService {


    @Autowired(required = false)
    private List<ITransService<?,?>> transServices;

    /**
     * 翻译入口
     * @param body
     */
    public Object trans(Object body){
        // 翻译支持
        if (!isEmpty(transServices)){
            if (body instanceof IPage) {
                List records = ((IPage) body).getRecords();
                if (!isEmpty(records)) {
                    for (Object record : records) {
                        transInner(record);
                    }
                }
            }else if(body instanceof List) {
                ((List) body).forEach(item->{
                    transInner(item);
                });

            }else{
                transInner(body);
            }
        }
        return body;
    }
    private void transInner(Object body){
        // 还可以添加一个TransPower注解用在需要翻译的类上，来标识该类的属性是否需要翻译，来加速性能，暂时未实现
        // 只翻译vo实体
        if (body == null) {
            return;
        }
        Class<?> bodyClass = body.getClass();
        transPower(body);
        transItemPower(body);
        for (Field field : ReflectUtil.getFields(bodyClass)) {
            // 翻译字段本身
            TransField transField = AnnotationUtil.getAnnotation(field, TransField.class);
            if(transField != null){
                trans(ReflectUtil.getFieldValue(body, field));
            }
            transByPower(field, body);
            transForPower(field, body);
        }
    }
    /**
     * transItem 支持
     * @param body
     */
    private void transPower(Object body){
        Trans trans = AnnotationUtil.getAnnotation(body.getClass(), Trans.class);
        if (trans != null) {
            if(trans.value() != null){
                for (TransItem transItem : trans.value()) {
                    doTrans(transItem.type(), transItem.byFieldName(),transItem.forFieldName(), body);
                }
            }
        }
    }

    /**
     * transItem 支持
     * @param body
     */
    private void transItemPower(Object body){
        TransItem transItem = AnnotationUtil.getAnnotation(body.getClass(), TransItem.class);
        if (transItem != null) {
            doTrans(transItem.type(), transItem.byFieldName(),transItem.forFieldName(), body);
        }
    }

    /**
     * TransBy 支持
     * @param field
     * @param body
     */
    private void transByPower(Field field, Object body){
        TransBy transBy = AnnotationUtil.getAnnotation(field, TransBy.class);
        if (transBy != null) {
            doTrans(transBy.type(), transBy.byFieldName(),field.getName(), body);
        }
    }

    /**
     * transFor 支持
     * @param field
     * @param body
     */
    private void transForPower(Field field,Object body){
        TransFor transFor = AnnotationUtil.getAnnotation(field, TransFor.class);
        if (transFor != null) {
            doTrans(transFor.type(), field.getName(), transFor.forFieldName(), body);
        }
    }

    private void doTrans(String type,String currentFieldName,String targetFieldName,Object body){
        for (ITransService transService : transServices) {
            if (transService.support(type)) {
                Object fieldValue = ReflectUtil.getFieldValue(body, currentFieldName);
                if (fieldValue != null) {
                    Object transValue = transService.trans(type,fieldValue);
                    ReflectUtil.setFieldValue(body,targetFieldName,transValue);
                }else {
                    log.debug("全局翻译doTrans未获取到值，requestId=[{}],class=[{}],fieldName=[{}]",
                            RequestIdTool.getRequestId(),body.getClass().getName(),currentFieldName);
                }
                break;
            }
        }
    }
}
