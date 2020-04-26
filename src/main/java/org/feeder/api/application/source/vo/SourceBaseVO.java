package org.feeder.api.application.source.vo;

import java.net.URL;
import java.util.Set;
import lombok.Data;

@Data
public class SourceBaseVO {

  private URL url;

  private Set<String> tags;

  private String title;
}
