package org.feeder.api.application.source.vo;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SourceResponseVO extends SourceBaseVO {

  private UUID id;
}
