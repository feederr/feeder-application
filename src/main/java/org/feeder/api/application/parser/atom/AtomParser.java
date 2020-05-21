package org.feeder.api.application.parser.atom;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.model.Channel;

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
  public Channel parse(InputStream is) {
    throw new UnsupportedOperationException("Not implemented!");
  }
}
