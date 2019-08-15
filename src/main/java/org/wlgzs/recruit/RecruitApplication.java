package org.wlgzs.recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("org.wlgzs.recruit.dao.*")
//注释部分打war包时需要
//@ServletComponentScan
//public class RecruitApplication extends SpringBootServletInitializer {
public class RecruitApplication {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RecruitApplication.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }
}
