package org.feeder.api.application.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopics {

  public static final String ITEM_VIEWED_TOPIC = "${feeder.kafka.topics.item-viewed}";

  public static final String ITEM_REMOVED_TOPIC = "${feeder.kafka.topics.item-removed}";

  public static final String CHANNEL_SUBSCRIBED_TOPIC = "${feeder.kafka.topics.channel-subscribed}";

  public static final String CHANNEL_UNSUBSCRIBED_TOPIC = "${feeder.kafka.topics.channel-unsubscribed}";

  public static final String CHANNEL_REMOVED_TOPIC = "${feeder.kafka.topics.channel-removed}";
}
