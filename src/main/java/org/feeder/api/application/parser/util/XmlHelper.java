package org.feeder.api.application.parser.util;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XmlHelper {

  public static String extractTagName(XMLEvent event) {
    if (isStartTag(event)) {
      return event.asStartElement().getName().getLocalPart();
    }
    return event.asEndElement().getName().getLocalPart();
  }

  public static boolean isStartTag(XMLEvent event) {
    return event.isStartElement();
  }

  public static boolean isEndTag(XMLEvent event) {
    return event.isEndElement();
  }

  public static String extractTagValue(XMLEventReader eventReader) throws XMLStreamException {
    XMLEvent event = eventReader.nextEvent();
    if (event instanceof Characters) {
      return event.asCharacters().getData();
    } else {
      return Strings.EMPTY;
    }
  }
}
