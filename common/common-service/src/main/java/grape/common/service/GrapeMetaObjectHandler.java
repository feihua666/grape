package grape.common.service;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import grape.common.Constants;
import grape.common.service.po.FieldFillDefault;
import grape.common.tools.ThreadContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import sun.plugin.com.TypeConverter;

import java.lang.reflect.Field;

/**
 * mybatisplus 字段填充插件实现
 * 填充表的默认值
 * Created by yangwei
 * Created at 2019/7/27 13:38
 */
@Component
@Slf4j
public class GrapeMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("插入操作 自动填充开始");
        int count = this.auotFill(metaObject,true);
        log.debug("插入操作 自动填充结束，填充了 {} 个字段",count);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("更新操作 自动填充开始");
        int count = this.auotFill(metaObject,false);
        log.debug("更新操作 自动填充结束，填充了 {} 个字段",count);
    }
    private int auotFill(MetaObject metaObject,boolean insert){
        int count = 0;
        Class<?> currentClass = metaObject.getOriginalObject().getClass();
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                // 插入情况
                if (field.isAnnotationPresent(FieldFillDefault.class)) {
                    FieldFillDefault fieldFillDefault = field.getAnnotation(FieldFillDefault.class);
                    Object value = null;
                    // 插入时
                    if (insert) {
                        value = fieldFillDefault.insert();
                    }else {
                        // 更新时
                        value = fieldFillDefault.update();
                    }
                    Object originValue = value;
                    if(!"".equals(value)){
                        if(FieldFillDefault.VAR_NOW.equals(value)){
                            value = System.currentTimeMillis();
                        }else if(FieldFillDefault.VAR_USERID.equals(value)){
                            value = ThreadContext.get(Constants.THREAD_CONTEXT_USER_ID_KEY);
                            if (value == null) {
                                log.debug("字段填充{}={},未找到当前登录用户id",field.getName(),originValue);
                            }
                        }
                        log.debug("字段填充{}={}",field.getName(),value);
                        count++;
                        value = TypeConverter.convertObject(field.getType(),value);
                        if (insert) {
                            this.setInsertFieldValByName(field.getName(), value, metaObject);
                        }else {
                            // 更新时
                            this.setUpdateFieldValByName(field.getName(), value, metaObject);
                        }

                    }else {
                        log.warn("配置了自动填充注解 @FieldFillDefault，但没有填写默认值，如果不需要自动填充默认值可以删除该注解，字段名={}",field.getName());
                    }
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return count;
    }
}
