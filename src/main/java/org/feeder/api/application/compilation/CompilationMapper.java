package org.feeder.api.application.compilation;

import java.util.UUID;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.application.compilation.vo.CompilationRequestVO;
import org.feeder.api.application.compilation.vo.CompilationResponseVO;
import org.feeder.api.core.mapper.BaseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CompilationMapper implements
    BaseMapper<Compilation, CompilationRequestVO, CompilationResponseVO> {

  private final ModelMapper mapper = new ModelMapper();

  public CompilationMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    mapper.createTypeMap(CompilationRequestVO.class, Compilation.class)
        .addMappings(mapping -> mapping.skip(Compilation::setChannels));
  }

  @Override
  public CompilationResponseVO toResponseVO(Compilation entity, Object... args) {
    return mapper.map(entity, CompilationResponseVO.class);
  }

  @Override
  public Compilation toEntity(CompilationRequestVO vo, Object... args) {

    UUID id = get(args, 0, UUID.class);

    Compilation entity = mapper.map(vo, Compilation.class);
    entity.setId(id);

    return entity;
  }

  @Override
  public void updateEntity(Compilation entity, CompilationRequestVO vo, Object... args) {
    mapper.map(vo, entity);
  }
}
