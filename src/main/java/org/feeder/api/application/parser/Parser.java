package org.feeder.api.application.parser;

import java.net.URL;
import org.feeder.api.application.channel.entity.Channel;

public interface Parser {

  Channel parse(URL url);
}
