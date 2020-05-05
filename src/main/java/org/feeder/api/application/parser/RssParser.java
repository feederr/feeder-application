package org.feeder.api.application.parser;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.feeder.api.application.channel.Channel;

// Bill Pugh's singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RssParser implements Parser {

  private static class RssParserLazyHolder {

    private static final RssParser INSTANCE = new RssParser();
  }

  public static RssParser getInstance() {
    return RssParserLazyHolder.INSTANCE;
  }

  @Override
  public Channel parse(InputStream is) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
