package org.feeder.api.application.parser.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

  private String title;

  private String description;

  private String link;

  private String pubDate;
}
