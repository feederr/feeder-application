package org.feeder.api.application;

import org.feeder.api.core.annotation.FeederService;
import org.springframework.boot.SpringApplication;

@FeederService
public class FeederApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeederApplication.class, args);
  }
}
