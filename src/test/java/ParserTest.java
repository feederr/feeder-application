import java.net.URL;
import java.net.URLConnection;
import lombok.SneakyThrows;
import org.feeder.api.application.parser.Parser;
import org.feeder.api.application.parser.ParserProvider;
import org.feeder.api.application.parser.ParserType;
import org.feeder.api.application.parser.model.Channel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserTest {

  @Test
  @SneakyThrows
  public void testContentType() {

    URL url = new URL("https://dianerehm.org/rss/npr/dr_podcast.xml");
    URLConnection urlConnection = url.openConnection();
    String contentType = urlConnection.getContentType();

    ParserProvider parserProvider = new ParserProvider();

    Parser parser = parserProvider.provide(ParserType.getByContentType(contentType));
    Channel channel = parser.parse(urlConnection.getInputStream());

    Assert.assertNotNull(channel);
  }
}
