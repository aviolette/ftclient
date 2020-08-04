package foodtruck.json;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author andrew@andrewviolette.net
 * @since 8/4/20
 */
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

  @Override
  public ZonedDateTime deserialize(JsonParser jp,
      DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec()
        .readTree(jp);
    return ZonedDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(node.asText()));
  }
}
