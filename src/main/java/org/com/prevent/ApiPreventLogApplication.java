package org.com.prevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "org.com.prevent.domain"
        })
@EnableJpaRepositories(basePackages = {
        "org.com.prevent.repository"
        })
public class ApiPreventLogApplication {

    private static final Logger log = LoggerFactory.getLogger(ApiPreventLogApplication.class);
   
    public static void main(String[] args) {
		SpringApplication.run(ApiPreventLogApplication.class, args);
	}

}
