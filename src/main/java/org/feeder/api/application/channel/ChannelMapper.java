package org.feeder.api.application.channel;

import static org.feeder.api.application.parser.ParserType.getByContentType;
import static org.feeder.api.application.parser.util.ContentTypeExtractor.extract;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.category.service.CategoryService;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.ParserProvider;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.util.UUIDUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelMapper implements BaseMapper<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ModelMapper mapper = new ModelMapper();

  private final ParserProvider provider;

  private final CategoryService categoryService;

  public ChannelMapper(ParserProvider provider,
      CategoryService categoryService) {

    this.provider = provider;
    this.categoryService = categoryService;

    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public ChannelResponseVO toResponseVO(Channel entity, Object... args) {
    return mapper.map(entity, ChannelResponseVO.class);
  }

  // SRP violation, put logic about fetching channel information to service
  @Override
  public Channel toEntity(ChannelRequestVO vo, Object... args) {

    try {

      UUID id = get(args, 0, UUID.class);

      Set<Category> categories = vo.getCategories().stream()
          .map(categoryService::getEntity)
          .collect(Collectors.toSet());

      URL url = new URL(vo.getLink());
      Parser parser = provider.provide(getByContentType(extract(url)));

      Channel entity = parser.parse(url.openStream());

      entity.setId(id);
      entity.getItems().forEach(item -> {
        item.setId(UUIDUtils.optimizedUUID());
        item.setNew(true);
      });
      entity.setNew(true);

      entity.addCategories(categories);

      return entity;

    } catch (IOException ex) {
      log.error("Unable to fetch channel contents", ex);
      throw new UncheckedIOException("Unable to fetch channel contents", ex);
    }
  }

  @Override
  public void updateEntity(Channel entity, ChannelRequestVO vo, Object... args) {

  }
}
