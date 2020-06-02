package org.feeder.api.application.compilation.sevice;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.compilation.CompilationMapper;
import org.feeder.api.application.compilation.CompilationRepository;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.application.compilation.vo.CompilationRequestVO;
import org.feeder.api.application.compilation.vo.CompilationResponseVO;
import org.feeder.api.core.exception.EntityNotFoundException;
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

  private final ChannelRepository channelRepository;

  @Override
  protected Compilation createEntity(CompilationRequestVO vo, UUID id, Object... args) {

    Compilation entity = mapper.toEntity(vo, id);

    Set<Channel> channels = getChannels(vo);

    entity.setChannels(channels);
    entity.setNew(true);

    return repository.save(entity);
  }

  @Override
  protected Compilation updateEntity(Compilation entity, CompilationRequestVO vo, Object... args) {

    mapper.updateEntity(entity, vo);

    Set<Channel> channels = getChannels(vo);

    entity.setChannels(channels);

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

  private Set<Channel> getChannels(CompilationRequestVO vo) {

    Set<Channel> channels = new HashSet<>();

    for (UUID channelId : vo.getChannels()) {
      Optional<Channel> channelOpt = channelRepository.findById(channelId);
      channels.add(
          channelOpt.orElseThrow(
              () -> new EntityNotFoundException(
                  String.format("%s = %s not found", Channel.class.getSimpleName(), channelId),
                  Channel.class,
                  channelId
              ))
      );
    }
    return channels;
  }
}
