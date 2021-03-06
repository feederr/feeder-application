package org.feeder.api.application.channel.event.external;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelBaseEvent {

  private UUID channelId;
}
