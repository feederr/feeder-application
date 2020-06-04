package org.feeder.api.application.item.event.internal;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ItemViewedApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = -2389756554069122138L;

  private final UUID itemId;

  private final UUID channelId;

  public ItemViewedApplicationEvent(Object source, UUID itemId, UUID channelId) {
    super(source);
    this.itemId = itemId;
    this.channelId = channelId;
  }
}
