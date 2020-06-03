package org.feeder.api.application.item.vo;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class ItemBaseVO {

  private String title;

  private String description;

  private String link;

  private LocalDateTime pubDate;
}
