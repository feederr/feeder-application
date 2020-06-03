package org.feeder.api.application.channel.event.outbound;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelSubscribedEvent extends ChannelBaseEvent {

  public ChannelSubscribedEvent(UUID channelId) {
    super(channelId);
  }
}
