package org.feeder.api.application.channel;

import java.util.UUID;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.core.search.JpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaSpecificationRepository<Channel, UUID> {

}
