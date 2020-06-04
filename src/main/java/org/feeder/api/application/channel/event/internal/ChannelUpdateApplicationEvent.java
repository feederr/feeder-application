package org.feeder.api.application.channel.event.internal;

import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelUpdateApplicationEvent extends ApplicationEvent {

  private static final long serialVersionUID = -3196852582708410676L;

  private final List<UUID> channelIds;

  public ChannelUpdateApplicationEvent(Object source, List<UUID> channelIds) {
    super(source);
    this.channelIds = channelIds;
  }
}
