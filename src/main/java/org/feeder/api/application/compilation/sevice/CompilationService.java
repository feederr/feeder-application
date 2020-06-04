package org.feeder.api.application.compilation.sevice;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.event.internal.ChannelSubscribedApplicationEvent;
import org.feeder.api.application.channel.event.internal.ChannelUnsubscribedApplicationEvent;
import org.feeder.api.application.compilation.CompilationMapper;
import org.feeder.api.application.compilation.CompilationRepository;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.application.compilation.vo.CompilationRequestVO;
import org.feeder.api.application.compilation.vo.CompilationResponseVO;
import org.feeder.api.core.exception.EntityNotFoundException;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompilationService extends
    BaseCrudService<Compilation, CompilationRequestVO, CompilationResponseVO> {

  private final CompilationMapper mapper;

  private final CompilationRepository repository;

  private final ChannelRepository channelRepository;

  private final ApplicationEventPublisher publisher;

  @Override
  protected Compilation createEntity(CompilationRequestVO vo, UUID id, Object... args) {

    Compilation entity = mapper.toEntity(vo, id);

    Set<Channel> channels = getChannels(vo);

    entity.setChannels(channels);
    entity.setNew(true);

    channels.forEach(
        channel -> publisher.publishEvent(
            new ChannelSubscribedApplicationEvent(this, channel.getId())
        )
    );

    return repository.save(entity);
  }

  @Override
  protected Compilation updateEntity(Compilation entity, CompilationRequestVO vo, Object... args) {

    mapper.updateEntity(entity, vo);

    Set<Channel> channels = getChannels(vo);

    Set<UUID> newChannels = channels.stream().map(Channel::getId)
        .collect(Collectors.toSet());
    Set<UUID> oldChannels = entity.getChannels().stream().map(Channel::getId)
        .collect(Collectors.toSet());

    Set<UUID> addedChannels = newChannels.stream()
        .filter(newChannel -> !oldChannels.contains(newChannel))
        .collect(Collectors.toSet());
    Set<UUID> removedChannels = oldChannels.stream()
        .filter(oldChannel -> !newChannels.contains(oldChannel))
        .collect(Collectors.toSet());

    addedChannels.forEach(
        channel -> publisher.publishEvent(
            new ChannelSubscribedApplicationEvent(this, channel)
        )
    );
    removedChannels.forEach(
        channel -> publisher.publishEvent(
            new ChannelUnsubscribedApplicationEvent(this, channel)
        )
    );

    entity.setChannels(channels);

    return repository.save(entity);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void delete(UUID id, Object... args) {

    log.debug("Delete {} = {}", getEntityClass().getSimpleName(), id);

    Compilation entity = getEntity(id, args);

    entity.getChannels().forEach(
        channel -> publisher.publishEvent(
            new ChannelUnsubscribedApplicationEvent(this, channel.getId())
        )
    );

    repository.delete(entity);
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
