package grape.mq.service;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangwei
 * Created at 2019/11/12 13:36
 */
@Configuration
@ComponentScan
public class MqServiceConfig {
    /**
     * demo队列请请勿用，主要用来做测试
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(QueueName.hello.name());
    }

}
