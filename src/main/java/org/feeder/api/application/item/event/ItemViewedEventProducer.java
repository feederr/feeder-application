package org.feeder.api.application.item.event;

import static org.feeder.api.application.common.KafkaTopics.ITEM_VIEWED_TOPIC;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemViewedEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value(ITEM_VIEWED_TOPIC)
  private String itemViewedTopic;

  public void produceItemViewedEvent(UUID itemId) {

    log.debug(
        "{} producing {} for item: {} to topic: {}",
        ItemViewedEventProducer.class.getSimpleName(),
        ItemViewedEvent.class.getSimpleName(),
        itemId,
        itemViewedTopic
    );

    kafkaTemplate.send(itemViewedTopic, new ItemViewedEvent(itemId));
  }
}
