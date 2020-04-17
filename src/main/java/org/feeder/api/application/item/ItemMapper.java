package org.feeder.api.application.item;

import java.util.UUID;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.application.item.vo.ItemRequestVO;
import org.feeder.api.application.item.vo.ItemResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements BaseMapper<Item, ItemRequestVO, ItemResponseVO> {

  private ModelMapper mapper = new ModelMapper();

  public ItemMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public ItemResponseVO toResponseVO(Item entity, Object... args) {
    return mapper.map(entity, ItemResponseVO.class);
  }

  @Override
  public Item toEntity(ItemRequestVO vo, Object... args) {

    UUID id = get(args, 0, UUID.class);

    Item entity = mapper.map(vo, Item.class);
    entity.setId(id);

    return entity;
  }

  @Override
  public void updateEntity(Item entity, ItemRequestVO vo, Object... args) {
    mapper.map(vo, entity);
  }
}
