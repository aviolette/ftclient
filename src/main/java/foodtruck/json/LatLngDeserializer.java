package foodtruck.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.util.LatLngConfig;

public class LatLngDeserializer extends JsonDeserializer<LatLng> {

  @Override
  public LatLng deserialize(JsonParser jp,
      DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec()
        .readTree(jp);
    return new LatLng(LatLngConfig.longToDouble(node.get("latitude")
        .asLong()), LatLngConfig.longToDouble(node.get("longitude")
        .asLong()));
  }
}
