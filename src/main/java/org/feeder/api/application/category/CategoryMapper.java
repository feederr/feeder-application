package org.feeder.api.application.category;

import java.util.UUID;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.category.vo.CategoryRequestVO;
import org.feeder.api.application.category.vo.CategoryResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements
    BaseMapper<Category, CategoryRequestVO, CategoryResponseVO> {

  private final ModelMapper mapper = new ModelMapper();

  public CategoryMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public CategoryResponseVO toResponseVO(Category entity, Object... args) {
    return mapper.map(entity, CategoryResponseVO.class);
  }

  @Override
  public Category toEntity(CategoryRequestVO vo, Object... args) {

    UUID id = get(args, 0, UUID.class);

    Category entity = mapper.map(vo, Category.class);
    entity.setId(id);

    return entity;
  }

  @Override
  public void updateEntity(Category entity, CategoryRequestVO vo, Object... args) {
    mapper.map(entity, vo);
  }
}
