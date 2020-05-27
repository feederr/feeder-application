package org.feeder.api.application.parser.util;

import java.io.IOException;
import java.net.URL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContentTypeExtractor {

  public static String extract(URL url) throws IOException {
    return url.openConnection().getContentType();
  }
}
