package org.feeder.api.application.category.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.category.CategoryMapper;
import org.feeder.api.application.category.CategoryRepository;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.category.vo.CategoryRequestVO;
import org.feeder.api.application.category.vo.CategoryResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService extends
    BaseCrudService<Category, CategoryRequestVO, CategoryResponseVO> {

  private final CategoryMapper mapper;

  private final CategoryRepository repository;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Category getEntity(UUID id, Object... args) {
    return super.getEntity(id, args);
  }

  @Override
  protected BaseMapper<Category, CategoryRequestVO, CategoryResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaRepository<Category, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Category> getEntityClass() {
    return Category.class;
  }
}
