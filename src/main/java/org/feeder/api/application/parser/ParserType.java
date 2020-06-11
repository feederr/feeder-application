package org.feeder.api.application.parser;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.parser.exception.ParserTypeNotFoundException;
import org.springframework.http.MediaType;

@RequiredArgsConstructor
@Getter
public enum ParserType {

  XML(MediaType.APPLICATION_XML),
  TEXT_XML(MediaType.TEXT_XML),
  RSS(MediaType.APPLICATION_RSS_XML),
  ATOM(MediaType.APPLICATION_ATOM_XML);

  private final MediaType value;

  public static ParserType getByContentType(String contentType) {
    return Arrays.stream(values())
        .filter(type -> contentType.contains(type.value.getType()))
        .findFirst()
        .orElseThrow(() -> new ParserTypeNotFoundException(
            String.format("Can't find %s for: %s", ParserType.class.getSimpleName(), contentType))
        );
  }
}
