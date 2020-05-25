package org.feeder.api.application.parser.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PubDateConverter {

  public static LocalDateTime toLocalDateTime(String pubDate) {
    return toLocalDateTime(pubDate, DateTimeFormatter.RFC_1123_DATE_TIME);
  }

  public static LocalDateTime toLocalDateTime(String pubDate, DateTimeFormatter formatter) {
    return LocalDateTime.parse(pubDate, formatter);
  }
}
