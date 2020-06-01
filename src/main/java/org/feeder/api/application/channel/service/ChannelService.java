package org.feeder.api.application.channel.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.feeder.api.application.channel.ChannelMapper;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.feeder.api.core.service.BaseCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService extends BaseCrudService<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ChannelMapper mapper;

  private final ChannelRepository repository;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Channel getEntity(UUID id, Object... args) {
    return super.getEntity(id, args);
  }

  @Override
  protected BaseMapper<Channel, ChannelRequestVO, ChannelResponseVO> getMapper() {
    return mapper;
  }

  @Override
  protected JpaSpecificationRepository<Channel, UUID> getRepository() {
    return repository;
  }

  @Override
  protected Class<Channel> getEntityClass() {
    return Channel.class;
  }
}
