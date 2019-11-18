package grape.mq.service.impl;

import grape.mq.service.api.ApiMqProducerService;
import grape.mq.service.dto.MessageParam;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangwei
 * Created at 2019/11/12 14:22
 */
@Component
public class ApiMqProducerServiceImpl implements ApiMqProducerService {
    @Autowired
    private AmqpTemplate amqpTemplate;

}
