package grape.workflow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将配置单独提出来，以实现被依赖时自动配置
 * Created by yangwei
 * Created at 2019/7/25 9:04
 */
@Slf4j
@Configuration
@ComponentScan
public class WorkFlowServiceConfig {

}
