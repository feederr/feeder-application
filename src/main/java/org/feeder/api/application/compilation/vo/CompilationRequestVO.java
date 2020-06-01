package org.feeder.api.application.compilation.vo;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompilationRequestVO extends CompilationBaseVO {

  private List<UUID> channels;
}
