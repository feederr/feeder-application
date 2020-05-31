package org.feeder.api.application.channel.vo;


import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelResponseVO extends ChannelBaseVO {

  private UUID id;

  private String title;

  private String description;

  private String author;

  private String copyright;

  private LocalDateTime pubDate;
}
