package org.feeder.api.application.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopics {

  public static final String ITEM_VIEWED_TOPIC = "${feeder.kafka.topics.item-viewed}";
}
