package org.feeder.api.application.channel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.core.domain.BaseEntity;

@Data
@Entity
@Builder
@Table(name = "channel")
@EqualsAndHashCode(callSuper = false)
public class Channel extends BaseEntity<UUID> {

  @EqualsAndHashCode.Exclude
  @Id
  @Column(name = "ch_id")
  private UUID id;

  @Column(name = "ch_title")
  private String title;

  @Size(max = 5000)
  @Column(name = "ch_description")
  private String description;

  @Size(max = 3000)
  @Column(name = "ch_link")
  private String link;

  @Column(name = "ch_author")
  private String author;

  @Column(name = "ch_copyright")
  private String copyright;

  @Column(name = "ch_pub_date")
  private LocalDateTime pubDate;

  @OneToMany(
      mappedBy = "channel",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Item> items;

  // TODO: add categories ManyToMany
//  @OneToMany(
//      fetch = FetchType.LAZY,
//      cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}
//  )
//  @JoinColumn(name = "ch_id")
//  private List<Category> categories;
  // TODO: add image
//  @OneToOne(
//      cascade = CascadeType.ALL,
//      orphanRemoval = true
//  )
//  @JoinColumn(name = "im_id")
//  private Image image;

  public void addItem(Item item) {
    if (Objects.isNull(items)) {
      items = new ArrayList<>();
    }
    items.add(item);
  }
}
