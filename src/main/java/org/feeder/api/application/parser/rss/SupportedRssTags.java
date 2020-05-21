package org.feeder.api.application.parser.rss;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SupportedRssTags {

  TITLE("title"),
  DESCRIPTION("description"),
  CHANNEL("channel"),
  COPYRIGHT("copyright"),
  LINK("link"),
  ITEM("item"),
  PUB_DATE("pubDate");

  private final String value;

  public static boolean isChannel(String tag) {
    return CHANNEL.value.equals(tag);
  }

  public static boolean isItem(String tag) {
    return ITEM.value.equals(tag);
  }

  public static boolean isDescription(String tag) {
    return DESCRIPTION.value.equals(tag);
  }

  public static boolean isTitle(String tag) {
    return TITLE.value.equals(tag);
  }

  public static boolean isLink(String tag) {
    return LINK.value.equals(tag);
  }

  public static boolean isPubDate(String tag) {
    return PUB_DATE.value.equals(tag);
  }

  public static boolean isCopyright(String tag) {
    return COPYRIGHT.value.equals(tag);
  }
}
