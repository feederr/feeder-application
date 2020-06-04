package org.feeder.api.application.item.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.item.ItemMapper;
import org.feeder.api.application.item.ItemRepository;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.event.internal.ItemRemovedApplicationEvent;
import org.feeder.api.application.item.event.internal.ItemViewedApplicationEvent;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService extends BaseCrudService<Item, ItemRequestVO, ItemResponseVO> {

  private final ItemRepository repository;

  private final ItemMapper mapper;

  private final ApplicationEventPublisher publisher;

  private final ChannelRepository channelRepository;

  @Override
  protected Item createEntity(ItemRequestVO vo, UUID id, Object... args) {

    Item entity = mapper.toEntity(vo, id);

    Channel channel = channelRepository.findById(vo.getChannelId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("%s = %s not found", Channel.class.getSimpleName(), vo.getChannelId()),
            Channel.class,
            vo.getChannelId()
        ));

    entity.setChannel(channel);
    entity.setNew(true);

    return repository.save(entity);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public ItemResponseVO get(UUID id, Object... args) {

    log.debug("Get {} = {}", getEntityClass().getSimpleName(), id);

    Item fetchedEntity = getEntity(id, args);

    publisher.publishEvent(
        new ItemViewedApplicationEvent(this, fetchedEntity.getId(),
            fetchedEntity.getChannel().getId())
    );

    return mapper.toResponseVO(fetchedEntity, args);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(UUID id, Object... args) {

    log.debug("Delete {} = {}", getEntityClass().getSimpleName(), id);

    Item entity = getEntity(id, args);

    publisher.publishEvent(new ItemRemovedApplicationEvent(this, id));

    repository.delete(entity);
  }

  @Override
  protected BaseMapper<Item, ItemRequestVO, ItemResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaSpecificationRepository<Item, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Item> getEntityClass() {
    return Item.class;
  }
}
