package org.feeder.api.application.channel.event.inbound;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelUnsubscribedApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = 963146840091772238L;

  private final UUID channelId;

  public ChannelUnsubscribedApplicationEvent(Object source, UUID channelId) {
    super(source);
    this.channelId = channelId;
  }
}
