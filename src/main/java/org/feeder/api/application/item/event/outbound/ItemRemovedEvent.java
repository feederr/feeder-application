package org.feeder.api.application.item.event.outbound;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRemovedEvent {

  private UUID itemId;
}
