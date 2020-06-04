package org.feeder.api.application.item.event.external;

import static org.feeder.api.application.common.KafkaTopics.ITEM_REMOVED_TOPIC;
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
public class ItemEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value(ITEM_VIEWED_TOPIC)
  private String itemViewedTopic;

  @Value(ITEM_REMOVED_TOPIC)
  private String itemRemovedTopic;

  public void produceItemViewedEvent(UUID itemId, UUID channelId) {

    log.debug(
        "{} producing {} for item: {} and channel: {} to topic: {}",
        ItemEventProducer.class.getSimpleName(),
        ItemViewedEvent.class.getSimpleName(),
        itemId,
        channelId,
        itemViewedTopic
    );

    kafkaTemplate.send(itemViewedTopic, new ItemViewedEvent(itemId, channelId));
  }

  public void produceItemRemovedEvent(UUID itemId) {

    log.debug(
        "{} producing {} for item: {} to topic: {}",
        ItemEventProducer.class.getSimpleName(),
        ItemRemovedEvent.class.getSimpleName(),
        itemId,
        itemRemovedTopic
    );

    kafkaTemplate.send(itemRemovedTopic, new ItemRemovedEvent(itemId));
  }
}
