package org.feeder.api.application.item.event.inbound;

import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.event.outbound.ItemEventProducer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ItemApplicationEventListener {

  private final ItemEventProducer producer;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void listenItemViewedEvent(ItemViewedApplicationEvent event) {
    producer.produceItemViewedEvent(event.getItemId(), event.getChannelId());
  }

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void listenItemRemovedEvent(ItemRemovedApplicationEvent event) {
    producer.produceItemRemovedEvent(event.getItemId());
  }
}
