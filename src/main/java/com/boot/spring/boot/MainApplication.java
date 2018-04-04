package com.boot.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by BHWL on 2017-08-09.
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(basePackages = {"com.myweb.dao.jpa"})
@ComponentScan(basePackages = {"com.myweb"})
@EntityScan(basePackages = {"com.myweb.pojo"})
public class MainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }
}
