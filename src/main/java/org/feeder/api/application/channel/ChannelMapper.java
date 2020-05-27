package org.feeder.api.application.channel;

import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.vo.ChannelRequestVO;
import org.feeder.api.application.channel.vo.ChannelResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

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
    throw new UnsupportedOperationException("Not implemented!");
  }

  @Override
  public void updateEntity(Channel entity, ChannelRequestVO vo, Object... args) {

  }
}
