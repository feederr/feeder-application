package org.feeder.api.application.item.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.ItemMapper;
import org.feeder.api.application.item.ItemRepository;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService extends BaseCrudService<Item, ItemRequestVO, ItemResponseVO> {

  private final ItemRepository repository;

  private final ItemMapper mapper;

  @Override
  protected BaseMapper<Item, ItemRequestVO, ItemResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaRepository<Item, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Item createEntity(ItemRequestVO vo, UUID id, Object... args) {

    Item entity = mapper.toEntity(vo, id);

    entity.setNew(true);

    return repository.save(entity);
  }

  @Override
  protected Item updateEntity(Item entity, ItemRequestVO vo, Object... args) {

    mapper.updateEntity(entity, vo);

    return repository.save(entity);
  }

  @Override
  protected Class<Item> getEntityClass() {
    return Item.class;
  }
}
