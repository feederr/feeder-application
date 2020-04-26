package org.feeder.api.application.source.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.source.SourceMapper;
import org.feeder.api.application.source.SourceRepository;
import org.feeder.api.application.source.entity.Source;
import org.feeder.api.application.source.vo.SourceRequestVO;
import org.feeder.api.application.source.vo.SourceResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceService extends BaseCrudService<Source, SourceRequestVO, SourceResponseVO> {

  private final SourceRepository repository;

  private final SourceMapper mapper;

  @Override
  protected BaseMapper<Source, SourceRequestVO, SourceResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaRepository<Source, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Source> getEntityClass() {
    return Source.class;
  }
}
