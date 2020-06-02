package org.feeder.api.application.channel.vo;

import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class ChannelBaseVO {

  // TODO: update name to rss link
  @Size(max = 500)
  private String link;
}
