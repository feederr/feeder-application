package org.feeder.api.application.compilation.vo;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompilationResponseVO extends CompilationBaseVO {

  private UUID id;
}
