package com.zup.processor;

import static java.util.Optional.ofNullable;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableScheduling
public class ZuppayProcessorMainApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuppayProcessorMainApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(AmqpAdmin admin) {
    return args -> {
      admin.declareQueue(new Queue(ofNullable(System.getenv("QUEUE_NAME")).orElse("payments")));
      Thread.sleep(5_000);
    };
  }
}
