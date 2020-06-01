package org.feeder.api.application.compilation.sevice;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.channel.service.ChannelService;
import org.feeder.api.application.compilation.CompilationMapper;
import org.feeder.api.application.compilation.CompilationRepository;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.application.compilation.vo.CompilationRequestVO;
import org.feeder.api.application.compilation.vo.CompilationResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompilationService extends
    BaseCrudService<Compilation, CompilationRequestVO, CompilationResponseVO> {

  private final CompilationMapper mapper;

  private final CompilationRepository repository;

  private final ChannelService channelService;

  @Override
  protected Compilation createEntity(CompilationRequestVO vo, UUID id, Object... args) {

    Compilation entity = mapper.toEntity(vo, id);

    vo.getChannels().stream()
        .map(channelService::getEntity)
        .forEach(entity::addChannel);

    entity.setNew(true);

    return repository.save(entity);
  }

  @Override
  protected Compilation updateEntity(Compilation entity, CompilationRequestVO vo, Object... args) {

    mapper.updateEntity(entity, vo);

    vo.getChannels().stream()
        .map(channelService::getEntity)
        .forEach(entity::addChannel);

    return repository.save(entity);
  }

  @Override
  protected BaseMapper<Compilation, CompilationRequestVO, CompilationResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaSpecificationRepository<Compilation, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Compilation> getEntityClass() {
    return Compilation.class;
  }
}
