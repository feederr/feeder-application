package org.feeder.api.application.channel.image.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Builder
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseEntity<UUID> {

  @EqualsAndHashCode.Exclude
  @Id
  @Column(name = "ch_id")
  private UUID id;

  @Column(name = "im_title")
  private String title;

  @Size(max = 3000)
  @Column(name = "im_url")
  private String url;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "ch_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Channel channel;

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "im_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "im_modified")
  private LocalDateTime modified;
}
