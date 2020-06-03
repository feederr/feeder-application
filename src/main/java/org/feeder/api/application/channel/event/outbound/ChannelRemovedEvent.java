package org.feeder.api.application.channel.event.outbound;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelRemovedEvent extends ChannelBaseEvent {

  public ChannelRemovedEvent(UUID channelId) {
    super(channelId);
  }
}
