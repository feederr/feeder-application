package org.feeder.api.application.parser;

import java.io.InputStream;
import org.feeder.api.application.parser.model.Channel;

public interface Parser {

  Channel parse(InputStream is);
}
