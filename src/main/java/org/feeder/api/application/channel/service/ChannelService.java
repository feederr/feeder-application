package org.feeder.api.application.channel.service;

import static org.feeder.api.application.parser.ParserType.getByContentType;
import static org.feeder.api.application.parser.util.ContentTypeExtractor.extract;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.category.service.CategoryService;
import org.feeder.api.application.channel.ChannelMapper;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.ParserProvider;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.service.BaseCrudService;
import org.feeder.api.core.util.UUIDUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelService extends BaseCrudService<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ChannelMapper mapper;

  private final ChannelRepository repository;

  private final ParserProvider provider;

  private final CategoryService categoryService;

  @Override
  protected Channel createEntity(ChannelRequestVO vo, UUID id, Object... args) {

    try {

      URL url = new URL(vo.getLink());
      Parser parser = provider.provide(getByContentType(extract(url)));

      Channel entity = parser.parse(url.openStream());

      entity.setId(id);
      entity.getItems().forEach(item -> {
        item.setId(UUIDUtils.optimizedUUID());
        item.setNew(true);
      });
      entity.setNew(true);

      return repository.save(entity);

    } catch (IOException ex) {
      log.error("Unable to fetch channel contents", ex);
      throw new UncheckedIOException("Unable to fetch channel contents", ex);
    }
  }

  @Override
  protected Channel updateEntity(Channel entity, ChannelRequestVO vo, Object... args) {
    throw new UnsupportedOperationException("Method not implemented");
  }

  @Override
  protected BaseMapper<Channel, ChannelRequestVO, ChannelResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaRepository<Channel, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Channel> getEntityClass() {
    return Channel.class;
  }
}
