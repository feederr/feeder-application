package org.feeder.api.application.channel.event.internal;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelSubscribedApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = -1941742169147065229L;

  private final UUID channelId;

  public ChannelSubscribedApplicationEvent(Object source, UUID channelId) {
    super(source);
    this.channelId = channelId;
  }
}
