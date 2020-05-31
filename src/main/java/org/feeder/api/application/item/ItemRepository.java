package org.feeder.api.application.item;

import java.util.UUID;
import org.feeder.api.application.item.entity.Item;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaSpecificationRepository<Item, UUID> {

}
