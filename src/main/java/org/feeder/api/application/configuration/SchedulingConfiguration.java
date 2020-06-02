package org.feeder.api.application.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(
    value = "feeder.scheduling.enabled", havingValue = "true", matchIfMissing = false
)
public class SchedulingConfiguration {

}
