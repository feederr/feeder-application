package org.feeder.api.application.compilation.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CompilationBaseVO {

  @Size(max = 30)
  @NotBlank
  private String name;
}
