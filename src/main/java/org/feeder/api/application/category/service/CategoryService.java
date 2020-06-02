package org.feeder.api.application.category.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.category.CategoryMapper;
import org.feeder.api.application.category.CategoryRepository;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.category.vo.CategoryRequestVO;
import org.feeder.api.application.category.vo.CategoryResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends
    BaseCrudService<Category, CategoryRequestVO, CategoryResponseVO> {

  private final CategoryMapper mapper;

  private final CategoryRepository repository;

  @Override
  protected BaseMapper<Category, CategoryRequestVO, CategoryResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaSpecificationRepository<Category, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Category> getEntityClass() {
    return Category.class;
  }
}
