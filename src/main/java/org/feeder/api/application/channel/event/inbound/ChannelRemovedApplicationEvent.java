package org.feeder.api.application.channel.event.inbound;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelRemovedApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = -1844015757903592624L;

  private final UUID channelId;

  public ChannelRemovedApplicationEvent(Object source, UUID channelId) {
    super(source);
    this.channelId = channelId;
  }
}
