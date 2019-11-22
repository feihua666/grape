package grape.common.service.trans.impl;

import cn.hutool.core.date.DateUtil;
import grape.common.service.trans.ITransService;
import org.springframework.stereotype.Component;

/**
 * 提供时间戳转日期格式
 * Created by yangwei
 * Created at 2019/11/21 17:38
 */
@Component
public class DatetimeTransServiceImpl implements ITransService<String,Long> {

    public static final String trans_date = "date";
    public static final String trans_datetime = "datetime";

    @Override
    public boolean support(String type) {
        return isEqualAny(type, trans_date, trans_datetime);
    }

    @Override
    public String trans(String type, Long key) {
        if(isEqual(trans_date,type)){
            // yyyy-MM-dd
            return DateUtil.formatDate(DateUtil.date(key));
        }else if(isEqual(trans_datetime,type)){
            // yyyy-MM-dd HH:mm:ss
            return DateUtil.formatDateTime(DateUtil.date(key));
        }
        return null;
    }
}
