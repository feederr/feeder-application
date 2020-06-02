package org.feeder.api.application.channel;

import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChannelMapper implements BaseMapper<Channel, ChannelRequestVO, ChannelResponseVO> {

  private final ModelMapper mapper = new ModelMapper();

  public ChannelMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public ChannelResponseVO toResponseVO(Channel entity, Object... args) {
    return mapper.map(entity, ChannelResponseVO.class);
  }

  @Override
  public Channel toEntity(ChannelRequestVO vo, Object... args) {
    // avoid implementation
    throw new UnsupportedOperationException("Mapping to channel entity not supported");
  }

  @Override
  public void updateEntity(Channel entity, ChannelRequestVO vo, Object... args) {
    // avoid implementation
    throw new UnsupportedOperationException("Update channel entity not supported");
  }
}
