package grape.common.service.mybatisplusfill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.List;

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
                if (this.getFieldValByName(mpFillConfig.getProperty(), metaObject) == null) {
                    this.setFieldValByName(mpFillConfig.getProperty(),mpFillConfig.getValue(),metaObject);
                }
            }
        }
    }
}
