package grape.common.service;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
        log.info("start insert fill ....");
        long current = System.currentTimeMillis();
        this.setInsertFieldValByName("version", 1, metaObject);
        this.setInsertFieldValByName("createAt",current , metaObject);
        this.setInsertFieldValByName("updateAt", current, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
