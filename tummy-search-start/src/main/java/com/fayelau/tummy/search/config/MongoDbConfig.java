package com.fayelau.tummy.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientOptions;

/**
 * Mongodb配置类 防止mongodb将链接断开
 * 
 * @author 3g7 2019-09-09 16:54:01
 * @version 0.0.1
 *
 */
@Configuration
public class MongoDbConfig {

    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder().maxConnectionIdleTime(60000).build();
    }

}
