package org.feeder.api.application.item.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.core.domain.BaseEntity;
import org.hibernate.annotations.Type;

@Data
@Entity
@Builder
@Table(name = "item")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity<UUID> {

  @EqualsAndHashCode.Exclude
  @Id
  @Column(name = "it_id", nullable = false)
  private UUID id;

  @Column(name = "it_title")
  private String title;

  @Type(type = "text")
  @Column(name = "it_description")
  @Basic( fetch = FetchType.LAZY )
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
