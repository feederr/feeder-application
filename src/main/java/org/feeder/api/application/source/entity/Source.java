package org.feeder.api.application.source.entity;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.core.converter.SetToStringConverter;
import org.feeder.api.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "source")
@EqualsAndHashCode(callSuper = false)
public class Source extends BaseEntity<UUID> {

  @Id
  @Column(name = "so_id", nullable = false)
  private UUID id;

  @Column(name = "so_url")
  private URL url;

  @Column(name = "so_tags")
  @Convert(converter = SetToStringConverter.class)
  private Set<String> tags;

  @Column(name = "so_title")
  private String title;
}
