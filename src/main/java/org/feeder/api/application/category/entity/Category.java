package org.feeder.api.application.category.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "category")
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseEntity<UUID> {

  @Id
  @Column(name = "ca_id")
  private UUID id;

  @NotEmpty
  @Size(max = 50)
  @Column(name = "ca_name")
  private String name;

  @Size(max = 3000)
  @Column(name = "ca_image_url")
  private String imageUrl;

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "ca_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "ca_modified")
  private LocalDateTime modified;
}
