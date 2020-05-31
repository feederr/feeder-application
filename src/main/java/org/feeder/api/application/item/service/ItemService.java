package org.feeder.api.application.item.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.item.ItemMapper;
import org.feeder.api.application.item.ItemRepository;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
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
  protected JpaSpecificationRepository<Item, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Item> getEntityClass() {
    return Item.class;
  }
}
