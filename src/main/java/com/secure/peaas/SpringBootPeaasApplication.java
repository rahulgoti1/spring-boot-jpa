package com.secure.peaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootPeaasApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootPeaasApplication.class, args);
  }
}
