package grape.common.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by yangwei
 * Created at 2020/1/3 16:14
 */
//@Configuration
@Data
public class CommonServiceDataSourceConfig {
    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    @NacosValue("#{druid.primary.driver-class-name}")
    private String driverClassName;

    /**
     * JDBC URL of the database.
     */
    private String url;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    @Primary
    @Bean
    public DataSource dataSourcePrimary(){
        DruidDataSource build = DruidDataSourceBuilder.create().build();

        build.setDriverClassName(driverClassName);
        build.setUrl(url);
        build.setUsername(username);
        build.setPassword(password);
        return build;
    }
}
