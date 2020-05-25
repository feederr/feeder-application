package org.feeder.api.application.item.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.domain.BaseEntity;

@Data
@Entity
@Builder
@Table(name = "item")
@EqualsAndHashCode(callSuper = false)
public class Item extends BaseEntity<UUID> {

  @Id
  @Column(name = "it_id", nullable = false)
  private UUID id;

  @Column(name = "it_title")
  private String title;

  @Column(name = "it_description")
  private String description;

  @Column(name = "it_link")
  private String link;

  @Column(name = "it_pub_date", nullable = false)
  private LocalDateTime pubDate;
}
