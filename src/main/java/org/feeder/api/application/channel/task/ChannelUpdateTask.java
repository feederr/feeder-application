package org.feeder.api.application.channel.task;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feeder.api.application.channel.ChannelRepository;
import org.feeder.api.application.channel.entity.Channel;
import org.feeder.api.application.channel.event.internal.ChannelUpdateApplicationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChannelUpdateTask {

  private final ChannelRepository channelRepository;

  private final ApplicationEventPublisher publisher;

  @Value("${feeder.scheduling.task.update-channels.batch-size:10}")
  private Integer batchSize;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  @Scheduled(cron = "${feeder.scheduling.task.update-channels.cron:0 */15 * * * *}")
  public void updateChannels() {

    Pageable pageRequest = PageRequest.of(0, batchSize);

    while (true) {

      Page<Channel> channelPage = channelRepository.findAll(pageRequest);

      if (!channelPage.isEmpty()) {
        ChannelUpdateApplicationEvent event = new ChannelUpdateApplicationEvent(this,
            channelPage.getContent()
                .stream()
                .map(Channel::getId)
                .collect(Collectors.toList())
        );
        publisher.publishEvent(event);
      }

      if (channelPage.hasNext()) {
        pageRequest = channelPage.nextPageable();
      } else {
        return;
      }
    }
  }
}
