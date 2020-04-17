package org.feeder.api.application.item;

import java.util.UUID;
import org.feeder.api.application.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

}
