package org.feeder.api.application.channel.event.outbound;

import static org.feeder.api.application.common.KafkaTopics.CHANNEL_REMOVED_TOPIC;
import static org.feeder.api.application.common.KafkaTopics.CHANNEL_SUBSCRIBED_TOPIC;
import static org.feeder.api.application.common.KafkaTopics.CHANNEL_UNSUBSCRIBED_TOPIC;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Value(CHANNEL_SUBSCRIBED_TOPIC)
  private String channelSubscribedTopic;

  @Value(CHANNEL_UNSUBSCRIBED_TOPIC)
  private String channelUnsubscribedTopic;

  @Value(CHANNEL_REMOVED_TOPIC)
  private String channelRemoveTopic;

  public void produceChannelSubscribedEvent(UUID channelId) {

    log.debug(
        "{} producing {} for channel: {} to topic: {}",
        ChannelEventProducer.class.getSimpleName(),
        ChannelSubscribedEvent.class.getSimpleName(),
        channelId,
        channelSubscribedTopic
    );

    kafkaTemplate.send(channelSubscribedTopic, new ChannelSubscribedEvent(channelId));
  }

  public void produceChannelUnsubscribedEvent(UUID channelId) {

    log.debug(
        "{} producing {} for channel: {} to topic: {}",
        ChannelEventProducer.class.getSimpleName(),
        ChannelSubscribedEvent.class.getSimpleName(),
        channelId,
        channelUnsubscribedTopic
    );

    kafkaTemplate.send(channelUnsubscribedTopic, new ChannelUnsubscribedEvent(channelId));
  }

  public void produceChannelRemovedEvent(UUID channelId) {

    log.debug(
        "{} producing {} for channel: {} to topic: {}",
        ChannelEventProducer.class.getSimpleName(),
        ChannelSubscribedEvent.class.getSimpleName(),
        channelId,
        channelRemoveTopic
    );

    kafkaTemplate.send(channelRemoveTopic, new ChannelRemovedEvent(channelId));
  }
}
