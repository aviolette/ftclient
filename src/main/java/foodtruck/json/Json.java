package foodtruck.json;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.javadocmd.simplelatlng.LatLng;

public class Json {
  public static ObjectMapper provideObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new SimpleModule()
        .addDeserializer(LatLng.class, new LatLngDeserializer())
        .addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer())
        .addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer()));
    return mapper;
  }
}
