package com.biscuits.doublecache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author biscuits
 */
@SpringBootApplication
public class DoubleCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoubleCacheApplication.class, args);
    }

}
