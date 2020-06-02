package org.feeder.api.application.channel.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.service.ChannelService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelUpdateEventListener {

  private final ChannelService channelService;

  @Async
  @EventListener
  @Transactional(propagation = Propagation.REQUIRED)
  public void listenChannelUpdateEvent(ChannelUpdateEvent event) {

    if (event == null) {
      log.warn("{} is null", ChannelUpdateEvent.class.getSimpleName());
    } else {
      log.debug("Updating channel contents for: \n{}", event.getPayload());
      List<Channel> channelsToUpdate = event.getPayload();
      channelsToUpdate.forEach(channelService::update);
    }
  }
}
