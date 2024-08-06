package com.utfpr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendAcervoMusicalApiApplication {
    private static final Logger log = LoggerFactory.getLogger(BackendAcervoMusicalApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendAcervoMusicalApiApplication.class, args);
    }
}
