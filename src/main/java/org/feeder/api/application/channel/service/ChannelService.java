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

@Service
@RequiredArgsConstructor
public class ChannelService extends BaseCrudService<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ChannelMapper mapper;

  private final ChannelRepository repository;

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
