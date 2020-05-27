package org.feeder.api.application.channel;

import java.util.UUID;
import org.feeder.api.application.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, UUID> {

}
