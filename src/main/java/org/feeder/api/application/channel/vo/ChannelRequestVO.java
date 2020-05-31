package org.feeder.api.application.channel.vo;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelRequestVO extends ChannelBaseVO {

  private List<UUID> categories;
}
