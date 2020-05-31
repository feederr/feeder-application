package org.feeder.api.application.channel.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.category.service.CategoryService;
import org.feeder.api.application.channel.ChannelMapper;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
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
public class ChannelService extends BaseCrudService<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ChannelMapper mapper;

  private final ChannelRepository repository;

  private final CategoryService categoryService;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Channel getEntity(UUID id, Object... args) {
    return super.getEntity(id, args);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page<ChannelResponseVO> getAllByCategory(UUID categoryId, Pageable pageable) {
    Category category = categoryService.getEntity(categoryId);
    return repository.findAllByCategoriesContaining(category, pageable)
        .map(entity -> mapper.toResponseVO(entity));
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

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page<ChannelResponseVO> getAllByDescription(String description, Pageable pageable) {
    return repository.findAllByDescriptionLike(description, pageable)
        .map(entity -> mapper.toResponseVO(entity));
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page<ChannelResponseVO> getAllByTitle(String title, Pageable pageable) {
    return repository.findAllByTitleLike(title, pageable)
        .map(entity -> mapper.toResponseVO(entity));
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Page<ChannelResponseVO> getAllByAuthor(String author, Pageable pageable) {
    return repository.findAllByAuthorLike(author, pageable)
        .map(entity -> mapper.toResponseVO(entity));
  }
}
