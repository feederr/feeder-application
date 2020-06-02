package org.feeder.api.application.channel.event;

import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.feeder.api.application.channel.entity.Channel;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChannelUpdateEvent extends ApplicationEvent {

  private static final long serialVersionUID = -3196852582708410676L;

  private List<UUID> payload;

  public ChannelUpdateEvent(Object source, List<UUID> payload) {
    super(source);
    this.payload = payload;
  }
}
