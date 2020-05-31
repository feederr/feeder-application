package org.feeder.api.application.channel.image.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Builder
@Table(name = "image")
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseEntity<UUID> {

  @Id
  @Column(name = "im_id")
  private UUID id;

  @Column(name = "im_title")
  private String title;

  @Size(max = 3000)
  @Column(name = "im_url")
  private String url;

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "im_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "im_modified")
  private LocalDateTime modified;
}
