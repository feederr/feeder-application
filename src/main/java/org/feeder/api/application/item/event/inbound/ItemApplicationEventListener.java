package org.feeder.api.application.item.event.inbound;

import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.event.outbound.ItemEventProducer;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemApplicationEventListener {

  private final ItemEventProducer producer;

  @Async
  @EventListener
  public void listenItemViewedEvent(ItemViewedApplicationEvent event) {
    producer.produceItemViewedEvent(event.getItemId(), event.getChannelId());
  }

  @Async
  @EventListener
  public void listenItemRemovedEvent(ItemRemovedApplicationEvent event) {
    producer.produceItemRemovedEvent(event.getItemId());
  }
}
