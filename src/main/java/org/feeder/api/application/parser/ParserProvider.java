package org.feeder.api.application.parser;

public class ParserProvider {

  public static Parser provide(ParserType type) {
    switch (type) {
      case RSS:
        return RssParser.getInstance();
      case ATOM:
        return AtomParser.getInstance();
      default:
        throw new UnsupportedParserException(
            String.format("Unsupported parser type: [%s]", type)
        );
    }
  }
}
