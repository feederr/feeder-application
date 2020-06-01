package org.feeder.api.application.compilation.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.core.tenancy.Tenantable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "compilation")
public class Compilation extends Tenantable<UUID> {

  @EqualsAndHashCode.Exclude
  @Id
  @Column(name = "co_id")
  private UUID id;

  @Size(max = 50)
  @Column(name = "co_name")
  private String name;

  @ManyToMany
  @JoinTable(
      name = "compilation_channel",
      joinColumns = @JoinColumn(name = "co_id"),
      inverseJoinColumns = @JoinColumn(name = "ch_id")
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Channel> channels = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @CreatedDate
  @Column(name = "co_created", updatable = false)
  private LocalDateTime created;

  @EqualsAndHashCode.Exclude
  @LastModifiedDate
  @Column(name = "co_modified")
  private LocalDateTime modified;

  public void addChannel(Channel channel) {
    this.channels.add(channel);
    channel.getCompilations().add(this);
  }

  public void removeChannel(Channel channel) {
    this.channels.remove(channel);
    channel.getCompilations().remove(this);
  }
}
