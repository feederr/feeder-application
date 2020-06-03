package org.feeder.api.application.channel.event.outbound;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelRemovedEvent extends ChannelBaseEvent {

  public ChannelRemovedEvent(UUID channelId) {
    super(channelId);
  }
}
