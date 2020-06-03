package org.feeder.api.application.channel.event.outbound;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChannelBaseEvent {

  private UUID channelId;
}
