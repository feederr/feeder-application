package org.feeder.api.application.channel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.feeder.api.application.category.entity.Category;
import org.feeder.api.application.channel.image.entity.Image;
import org.feeder.api.application.compilation.entity.Compilation;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.core.domain.BaseEntity;

@Data
@Entity
@Table(name = "channel")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
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

  @Size(max = 500)
  @Column(name = "ch_link")
  private String link;

  @Size(max = 500)
  @Column(name = "ch_rss_link")
  private String rssLink;

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
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<Item> items = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "channel_category",
      joinColumns = @JoinColumn(name = "ch_id"),
      inverseJoinColumns = @JoinColumn(name = "ca_id")
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Category> categories = new HashSet<>();

  @ManyToMany(mappedBy = "channels")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Compilation> compilations = new HashSet<>();

  @OneToOne(
      mappedBy = "channel",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Image image;

  public void addItem(Item item) {
    this.items.add(item);
    item.setChannel(this);
  }

  public void setItems(List<Item> items) {
    items.forEach(this::addItem);
  }

  public void removeItem(Item item) {
    this.items.remove(item);
    item.setChannel(null);
  }

  public void removeItems(List<Item> items) {
    items.forEach(this::removeItem);
  }

  public void addCategory(Category category) {
    this.categories.add(category);
  }

  public void setCategories(Collection<Category> categories) {
    categories.forEach(this::addCategory);
  }

  public void removeCategory(Category category) {
    this.categories.remove(category);
  }

  public void setImage(Image image) {
    this.image = image;

    if (image != null) {
      image.setChannel(this);
    }
  }

  public void removeCategories(Collection<Category> categories) {
    categories.forEach(this::removeCategory);
  }
}
