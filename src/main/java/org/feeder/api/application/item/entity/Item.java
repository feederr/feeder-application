package org.feeder.api.application.item.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.core.domain.BaseEntity;

@Data
@Entity
@Builder
@Table(name = "item")
@EqualsAndHashCode(callSuper = false)
public class Item extends BaseEntity<UUID> {

  @EqualsAndHashCode.Exclude
  @Id
  @Column(name = "it_id", nullable = false)
  private UUID id;

  @Column(name = "it_title")
  private String title;

  @Size(max = 5000)
  @Column(name = "it_description")
  private String description;

  @Size(max = 3000)
  @Column(name = "it_link")
  private String link;

  @Column(name = "it_pub_date", nullable = false)
  private LocalDateTime pubDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ch_id")
  private Channel channel;
}
