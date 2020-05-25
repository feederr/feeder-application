package org.feeder.api.application.category.vo;


import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryResponseVO extends CategoryBaseVO {

  private UUID id;
}
