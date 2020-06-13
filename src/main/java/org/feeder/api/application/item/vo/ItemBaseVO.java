package org.feeder.api.application.item.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class ItemBaseVO {

  private String title;

  private String description;

  private String link;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime pubDate;
}
