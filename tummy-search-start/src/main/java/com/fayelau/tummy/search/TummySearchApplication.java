package com.fayelau.tummy.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 肚子平台查询模块启动类
 * 
 * @author 3g7 2019-09-09 10:59:13
 * @version 0.0.1
 *
 */
@SpringBootApplication
@ServletComponentScan
public class TummySearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TummySearchApplication.class, args);
    }

}
 