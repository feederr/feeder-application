package org.feeder.api.application.item.event.inbound;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ItemRemovedApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = -9115023239806941942L;

  private final UUID itemId;

  public ItemRemovedApplicationEvent(Object source, UUID itemId) {
    super(source);
    this.itemId = itemId;
  }
}
