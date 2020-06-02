package org.feeder.api.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTestProducer {

  private final KafkaTemplate<String, TestClass> kafkaTemplate;

  public void produceTestMessage() {
    log.debug("Sending test message to statistics");
    kafkaTemplate.send("test", new TestClass("ping"));
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TestClass {

    private String message;
  }
}
