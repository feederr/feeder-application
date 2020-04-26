package org.feeder.api.application.source;

import java.util.UUID;
import org.feeder.api.application.source.entity.Source;
import org.feeder.api.application.source.vo.SourceRequestVO;
import org.feeder.api.application.source.vo.SourceResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class SourceMapper implements BaseMapper<Source, SourceRequestVO, SourceResponseVO> {

  private ModelMapper mapper = new ModelMapper();

  public SourceMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public SourceResponseVO toResponseVO(Source entity, Object... args) {
    return mapper.map(entity, SourceResponseVO.class);
  }

  @Override
  public Source toEntity(SourceRequestVO vo, Object... args) {
    UUID id = get(args, 0, UUID.class);

    Source entity = mapper.map(vo, Source.class);
    entity.setId(id);

    return entity;
  }

  @Override
  public void updateEntity(Source entity, SourceRequestVO vo, Object... args) {
    mapper.map(vo, entity);
  }
}
