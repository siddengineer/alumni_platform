package com.college.platform.alumni_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching
public class AlumniPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlumniPlatformApplication.class, args);
    }
}
