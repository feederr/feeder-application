package org.feeder.api.application.channel.service;

import static org.feeder.api.application.parser.ParserType.getByContentType;
import static org.feeder.api.application.parser.util.ContentTypeExtractor.extract;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.category.CategoryRepository;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.channel.ChannelMapper;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.event.inbound.ChannelRemovedApplicationEvent;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.ParserProvider;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.feeder.api.core.util.UUIDUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelService extends BaseCrudService<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ChannelMapper mapper;

  private final ChannelRepository repository;

  private final ParserProvider provider;

  private final CategoryRepository categoryRepository;

  private final ApplicationEventPublisher publisher;

  @Override
  protected Channel createEntity(ChannelRequestVO vo, UUID id, Object... args) {

    try {

      Set<Category> categories = getCategories(vo);

      Channel entity = getChannel(vo.getLink());

      entity.setId(id);
      entity.setRssLink(vo.getLink());
      entity.getItems().forEach(item -> {
        item.setId(UUIDUtils.optimizedUUID());
        item.setNew(true);
      });
      entity.setNew(true);

      entity.addCategories(categories);

      return repository.save(entity);

    } catch (IOException ex) {
      log.error("Unable to fetch channel contents", ex);
      throw new UncheckedIOException("Unable to fetch channel contents", ex);
    }
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void update(UUID id) {
    try {

      Optional<Channel> savedChannelOpt = repository.findById(id);

      if (savedChannelOpt.isPresent()) {

        Channel savedChannel = savedChannelOpt.get();
        Channel recentChannel = getChannel(savedChannel.getRssLink());

        if (recentChannel.getPubDate().isAfter(savedChannel.getPubDate())) {

          List<Item> recentItems = recentChannel.getItems();
          List<Item> existingItems = savedChannel.getItems();
          List<Item> itemsToAdd = new ArrayList<>();

          for (Item recentItem : recentItems) {
            if (isUniqueItem(existingItems, recentItem)) {
              itemsToAdd.add(recentItem);
            }
          }

          if (!itemsToAdd.isEmpty()) {
            itemsToAdd.forEach(item -> {
              item.setId(UUIDUtils.optimizedUUID());
              item.setNew(true);
            });
          }

          savedChannel.setPubDate(recentChannel.getPubDate());
          savedChannel.setAuthor(recentChannel.getAuthor());
          savedChannel.setDescription(recentChannel.getDescription());
          savedChannel.setCopyright(recentChannel.getCopyright());
          savedChannel.setTitle(recentChannel.getTitle());
          savedChannel.addItems(itemsToAdd);

          repository.save(savedChannel);
        }
      }
    } catch (IOException ex) {
      log.error("Unable to fetch channel contents", ex);
      throw new UncheckedIOException("Unable to fetch channel contents", ex);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(UUID id, Object... args) {

    log.debug("Delete {} = {}", getEntityClass().getSimpleName(), id);

    Channel entity = getEntity(id, args);

    publisher.publishEvent(new ChannelRemovedApplicationEvent(this, id));

    repository.delete(entity);
  }

  @Override
  public ChannelResponseVO update(ChannelRequestVO channelRequestVO, UUID id, Object... args) {
    // avoid implementation
    throw new UnsupportedOperationException("Channel update not supported");
  }

  @Override
  protected BaseMapper<Channel, ChannelRequestVO, ChannelResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaSpecificationRepository<Channel, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Channel> getEntityClass() {
    return Channel.class;
  }

  private Set<Category> getCategories(ChannelRequestVO vo) {

    Set<Category> categories = new HashSet<>();

    for (UUID categoryId : vo.getCategories()) {
      Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
      categories.add(
          categoryOpt.orElseThrow(
              () -> new EntityNotFoundException(
                  String.format("%s = %s not found", Category.class.getSimpleName(), categoryId),
                  Category.class,
                  categoryId
              ))
      );
    }

    return categories;
  }

  private Channel getChannel(String link) throws IOException {

    URL url = new URL(link);
    Parser parser = provider.provide(getByContentType(extract(url)));

    return parser.parse(url.openStream());
  }

  private boolean isUniqueItem(List<Item> existingItems, Item recentItem) {
    return existingItems.stream()
        .noneMatch(item -> item.getTitle().equals(recentItem.getTitle())
            && item.getPubDate().isEqual(recentItem.getPubDate())
        );
  }
}
