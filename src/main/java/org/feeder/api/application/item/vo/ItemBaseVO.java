package org.feeder.api.application.item.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ItemBaseVO {

  private String title;

  private String description;

  private String link;

  private LocalDateTime pubDate;
}
