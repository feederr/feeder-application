package org.feeder.api.application.parser.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Channel {

  private String title;

  private String link;

  private String pubDate;

  private String copyright;

  private String description;
}
