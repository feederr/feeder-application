package org.feeder.api.application.channel.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
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

  private ImageVO image;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime pubDate;
}
