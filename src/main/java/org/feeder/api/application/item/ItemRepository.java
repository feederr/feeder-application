package org.feeder.api.application.item;

import java.util.UUID;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

  Page<Item> findAllByChannel(Channel channel, Pageable pageable);
}
