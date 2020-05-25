package org.feeder.api.application.parser.rss;

import static org.feeder.api.application.parser.rss.SupportedRssTags.COPYRIGHT;
import static org.feeder.api.application.parser.rss.SupportedRssTags.DESCRIPTION;
import static org.feeder.api.application.parser.rss.SupportedRssTags.LINK;
import static org.feeder.api.application.parser.rss.SupportedRssTags.PUB_DATE;
import static org.feeder.api.application.parser.rss.SupportedRssTags.TITLE;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isCopyright;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isDescription;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isItem;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isLink;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isPubDate;
import static org.feeder.api.application.parser.rss.SupportedRssTags.isTitle;
import static org.feeder.api.application.parser.util.XmlHelper.extractTagName;
import static org.feeder.api.application.parser.util.XmlHelper.extractTagValue;
import static org.feeder.api.application.parser.util.XmlHelper.isEndTag;
import static org.feeder.api.application.parser.util.XmlHelper.isStartTag;
import java.io.InputStream;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.exception.ParseFailedException;
import org.feeder.api.application.parser.model.Channel;
import org.feeder.api.application.parser.util.PubDateConverter;

// Bill Pugh's singleton
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RssParser implements Parser {

  /*
    Temporary NOTE:

    URL url = new URL("https://rss.art19.com/the-bill-simmons-podcast");
    URLConnection urlConnection = url.openConnection();
    String contentType = urlConnection.getContentType();

    ^ guide on how to determine content type
   */

  private static class RssParserLazyHolder {

    private static final RssParser INSTANCE = new RssParser();
  }

  public static RssParser getInstance() {
    return RssParserLazyHolder.INSTANCE;
  }

  @Override
  public Channel parse(InputStream is) {

    Channel channel = null;

    try {

      var inputFactory = XMLInputFactory.newInstance();
      var reader = inputFactory.createXMLEventReader(is);
      var context = new HashMap<SupportedRssTags, String>(SupportedRssTags.values().length);

      while (reader.hasNext()) {

        XMLEvent event = reader.nextEvent();

        if (isStartTag(event)) {

          String tagName = extractTagName(event);

          if (isItem(tagName) && channel == null) {

            channel = Channel.builder()
                .copyright(context.get(COPYRIGHT))
                .description(context.get(DESCRIPTION))
                .link(context.get(LINK))
                .pubDate(context.get(PUB_DATE))
                .title(context.get(TITLE))
                .build();

            context.clear();

          } else if (isCopyright(tagName)) {
            context.put(COPYRIGHT, extractTagValue(reader));
          } else if (isLink(tagName)) {
            context.put(LINK, extractTagValue(reader));
          } else if (isDescription(tagName)) {
            context.put(DESCRIPTION, extractTagValue(reader));
          } else if (isPubDate(tagName)) {
            context.put(PUB_DATE, extractTagValue(reader));
          } else if (isTitle(tagName)) {
            context.put(TITLE, extractTagValue(reader));
          }

          continue;
        }

        if (isEndTag(event)) {

          String tagName = extractTagName(event);

          if (isItem(tagName)) {

            Item item = Item.builder()
                .description(context.get(DESCRIPTION))
                .link(context.get(LINK))
                .pubDate(PubDateConverter.toLocalDateTime(context.get(PUB_DATE)))
                .title(context.get(TITLE))
                .build();

            if (channel == null) {
              throw new ParseFailedException("Incorrect tag structure in provided file");
            }

            // TODO: add channel
//            channel.addItem(item);
            context.clear();
          }
        }
      }

    } catch (XMLStreamException ex) {
      log.warn("Parsing failed with: {}", ex.getMessage(), ex);
      throw new ParseFailedException(ex);
    }

    return channel;
  }
}
