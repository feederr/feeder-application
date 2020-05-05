package org.feeder.api.application.parser;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.feeder.api.application.channel.Channel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AtomParser implements Parser {

  private static class AtomParserLazyHolder {

    private static final AtomParser INSTANCE = new AtomParser();
  }

  public static AtomParser getInstance() {
    return AtomParserLazyHolder.INSTANCE;
  }

  @Override
  public Channel parse(InputStream is) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
