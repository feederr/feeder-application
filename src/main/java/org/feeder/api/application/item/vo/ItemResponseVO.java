package org.feeder.api.application.item.vo;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemResponseVO extends ItemBaseVO {

  private UUID id;
}
