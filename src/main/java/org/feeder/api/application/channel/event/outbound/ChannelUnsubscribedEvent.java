package org.feeder.api.application.channel.event.outbound;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelUnsubscribedEvent extends ChannelBaseEvent {

  public ChannelUnsubscribedEvent(UUID channelId) {
    super(channelId);
  }
}
