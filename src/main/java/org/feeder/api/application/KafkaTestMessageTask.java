package org.feeder.api.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaTestMessageTask {

  private final KafkaTestProducer kafkaTestProducer;

  @Scheduled(fixedRate = 1000)
  public void publishTestMessage() {
    kafkaTestProducer.produceTestMessage();
  }
}
