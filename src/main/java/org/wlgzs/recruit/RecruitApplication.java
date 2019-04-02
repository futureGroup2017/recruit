package org.wlgzs.recruit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class RecruitApplication {
    private static final Logger logger = LoggerFactory.getLogger(RecruitApplication.class);

    public static void main(String[] args) {
        logger.info("SpringBoot开始加载");
        SpringApplication.run(RecruitApplication.class, args);
        logger.info("SpringBoot加载完毕");
    }
}
