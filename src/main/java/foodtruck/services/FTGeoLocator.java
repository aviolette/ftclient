package foodtruck.services;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import foodtruck.json.Json;
import foodtruck.models.Location;

public class FTGeoLocator implements GeoLocator {

  private static final Logger log = Logger.getLogger(FTGeoLocator.class.getName());

  @Override
  public Optional<Location> broadSearch(String name) {
    var client = HttpClient.newHttpClient();
    try {
      String filter = URLEncoder.encode(name, Charset.defaultCharset());
      var request = java.net.http.HttpRequest.newBuilder()
          .uri(URI.create("https://www.chicagofoodtruckfinder.com/services/v2/locations?name=" + filter))
          .header("Accept", "application/json")
          .header("x-ftf-secret", System.getenv().get("FOODTRUCK_API_SECRET"))
          .GET()
          .build();
      var documentResponse = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
      if (documentResponse.statusCode() >= 400) {
        log.log(Level.SEVERE, "Response code: " + documentResponse.statusCode());
        log.log(Level.SEVERE, documentResponse.body());
        throw new RuntimeException("Could not connect to address matcher");
      }
      ObjectMapper mapper = Json.provideObjectMapper();
      var items = mapper.readValue(documentResponse.body(), Location[].class);
      if (items.length == 0) {
        return Optional.empty();
      }
      return Optional.of(items[0]);
    } catch (IOException | InterruptedException e) {
      log.log(Level.SEVERE, e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
