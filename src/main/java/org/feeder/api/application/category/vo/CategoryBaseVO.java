package org.feeder.api.application.category.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryBaseVO {

  @Size(max = 30)
  @NotEmpty
  private String name;

  private String imageUrl;
}
