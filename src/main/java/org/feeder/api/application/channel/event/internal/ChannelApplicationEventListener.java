package org.feeder.api.application.channel.event.internal;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.event.external.ChannelEventProducer;
import org.feeder.api.application.channel.service.ChannelService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelApplicationEventListener {

  private final ChannelEventProducer producer;

  private final ChannelService channelService;

  @Async
  @EventListener
  @Transactional(propagation = Propagation.REQUIRED)
  public void listenChannelUpdateEvent(ChannelUpdateApplicationEvent event) {

    if (event == null) {
      log.warn("{} is null", ChannelUpdateApplicationEvent.class.getSimpleName());
    } else {
      log.debug("Updating channel contents for: \n{}", event.getChannelIds());
      List<UUID> channelsToUpdate = event.getChannelIds();
      channelsToUpdate.forEach(channelService::update);
    }
  }

  @Async
  @EventListener
  public void listenChannelSubscribedEvent(ChannelSubscribedApplicationEvent event) {
    producer.produceChannelSubscribedEvent(event.getChannelId());
  }

  @Async
  @EventListener
  public void listenChannelUnsubscribedEvent(ChannelUnsubscribedApplicationEvent event) {
    producer.produceChannelUnsubscribedEvent(event.getChannelId());
  }

  @Async
  @EventListener
  public void listenChannelRemovedEvent(ChannelRemovedApplicationEvent event) {
    producer.produceChannelRemovedEvent(event.getChannelId());
  }
}
