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
import java.util.List;
import java.util.Map;

/**
 * mybatisplus 字段填充插件实现
 * 填充表的默认值
 * Created by yangwei
 * Created at 2019/7/27 13:38
 */
@Component
@Slf4j
public class MpMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("插入操作 自动填充开始");
        this.auotFill(metaObject,true);
        log.debug("插入操作 自动填充结束");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("更新操作 自动填充开始");
        this.auotFill(metaObject,false);
        log.debug("更新操作 自动填充结束");
    }
    private void auotFill(MetaObject metaObject,boolean insert){
        List<MpFillConfig> config = MpFillConfig.getConfig();
        for (MpFillConfig mpFillConfig : config) {
            if((insert && mpFillConfig.isInsert())|| (!insert && mpFillConfig.isUpdate())){
                this.setFieldValByName(mpFillConfig.getProperty(),mpFillConfig.getValue(),metaObject);
            }
        }
    }


}
