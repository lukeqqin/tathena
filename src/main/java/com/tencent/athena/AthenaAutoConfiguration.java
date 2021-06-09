package com.tencent.athena;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置athena模块自动装配
 *
 * @author qinzhongjian
 */
@Configuration
public class AthenaAutoConfiguration {

    @Bean
    public AthenaConfiguration athenaConfiguration() {
        return new AthenaConfiguration();
    }
}
