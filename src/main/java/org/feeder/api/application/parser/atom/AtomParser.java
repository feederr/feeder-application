package org.feeder.api.application.parser.atom;

import java.net.URL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.parser.Parser;

// Bill Pugh's singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AtomParser implements Parser {

  private static class AtomParserLazyHolder {

    private static final AtomParser INSTANCE = new AtomParser();
  }

  public static AtomParser getInstance() {
    return AtomParserLazyHolder.INSTANCE;
  }

  @Override
  public Channel parse(URL url) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
