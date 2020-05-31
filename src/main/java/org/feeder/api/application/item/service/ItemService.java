package org.feeder.api.application.item.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.service.ChannelService;
import org.feeder.api.application.item.ItemMapper;
import org.feeder.api.application.item.ItemRepository;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService extends BaseCrudService<Item, ItemRequestVO, ItemResponseVO> {

  private final ItemRepository repository;

  private final ItemMapper mapper;

  private final ChannelService channelService;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page<ItemResponseVO> getAllByChannel(UUID channelId, Pageable pageable) {
    Channel channel = channelService.getEntity(channelId);
    return repository.findAllByChannel(channel, pageable)
        .map(entity -> mapper.toResponseVO(entity));
  }

  @Override
  protected BaseMapper<Item, ItemRequestVO, ItemResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaRepository<Item, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Item> getEntityClass() {
    return Item.class;
  }
}
