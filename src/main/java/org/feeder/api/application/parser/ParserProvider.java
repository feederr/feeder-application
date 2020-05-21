package org.feeder.api.application.parser;

import org.feeder.api.application.parser.atom.AtomParser;
import org.feeder.api.application.parser.exception.UnsupportedParserException;
import org.feeder.api.application.parser.rss.RssParser;
import org.springframework.stereotype.Component;

@Component
public class ParserProvider {

  public Parser provide(ParserType type) {
    return switch (type) {
      case RSS:
        yield RssParser.getInstance();
      case ATOM:
        yield AtomParser.getInstance();
      default:
        throw new UnsupportedParserException(
            String.format("Unsupported parser type: [%s]", type)
        );
    };
  }
}
