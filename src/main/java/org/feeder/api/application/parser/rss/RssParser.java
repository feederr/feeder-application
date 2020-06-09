package org.feeder.api.application.parser.rss;

import static org.feeder.api.application.parser.util.PubDateConverter.toLocalDateTime;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.image.entity.Image;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.exception.ParseFailedException;

// Bill Pugh's singleton
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RssParser implements Parser {

  private static class RssParserLazyHolder {

    private static final RssParser INSTANCE = new RssParser();
  }

  public static RssParser getInstance() {
    return RssParserLazyHolder.INSTANCE;
  }

  // TODO: add image
  @Override
  public Channel parse(URL url) {
    try {

      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(url));

      Channel channel = new Channel();

      channel.setTitle(feed.getTitle());
      channel.setDescription(feed.getDescription());
      channel.setLink(feed.getLink());
      channel.setCopyright(feed.getCopyright());
      channel.setPubDate(toLocalDateTime(feed.getPublishedDate()));
      channel.setAuthor(feed.getAuthor());
      channel.setImage(mapImage(feed.getImage()));
      channel.setItems(mapItems((List<SyndEntry>) feed.getEntries()));

      return channel;

    } catch (FeedException | IOException ex) {
      log.warn("Parsing failed with: {}", ex.getMessage(), ex);
      throw new ParseFailedException("Parsing failed", ex);
    }
  }

  private List<Item> mapItems(List<SyndEntry> syndEntries) {
    return syndEntries.stream()
        .map(this::mapItem)
        .collect(Collectors.toList());
  }

  private Item mapItem(SyndEntry entry) {
    return Item.builder()
        .title(entry.getTitle())
        .description(
            Optional.ofNullable(entry.getDescription())
                .map(SyndContent::getValue)
                .orElse(null)
        )
        .link(entry.getLink())
        .pubDate(toLocalDateTime(entry.getPublishedDate()))
        .build();
  }

  private Image mapImage(SyndImage syndImage) {
    return Optional.ofNullable(syndImage)
        .map(image -> Image.builder()
            .title(image.getTitle())
            .url(image.getUrl())
            .build())
        .orElse(null);
  }
}
