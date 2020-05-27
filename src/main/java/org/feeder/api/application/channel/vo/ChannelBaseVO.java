package org.feeder.api.application.channel.vo;

import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class ChannelBaseVO {

  @Size(max = 3000)
  private String link;
}
