package foodtruck.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import foodtruck.json.Json;

public class HttpAddressExtractor implements AddressExtractor {

  private static final Logger log = Logger.getLogger(HttpAddressExtractor.class.getName());

  @Override
  public List<String> parse(String text, String truckId) {
    var client = HttpClient.newHttpClient();
    ObjectMapper mapper = Json.provideObjectMapper();
    try {
      var post = mapper.writeValueAsString(Map.of("text", text, "truckId", truckId));
      var addressLookupUrl = System.getenv().get("ADDRESS_LOOKUP_URL");
      var request = HttpRequest.newBuilder()
          .uri(URI.create(addressLookupUrl))
          .header("Content-Type", "application/json")
          .header("x-ftf-secret", System.getenv().get("FOODTRUCK_API_SECRET"))
          .POST(HttpRequest.BodyPublishers.ofString(post))
          .build();
      var documentResponse = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
      if (documentResponse.statusCode() >= 400) {
        log.log(Level.SEVERE, "Response code: " + documentResponse.statusCode());
        log.log(Level.SEVERE, documentResponse.body());
        throw new RuntimeException("Could not connect to address matcher");
      }
      var json = mapper.readTree(documentResponse.body());
      var items = new LinkedList<String>();

      for (Iterator<JsonNode> it = json.elements(); it.hasNext(); ) {
        items.add(it.next().asText());
      }
      return items;
    } catch (IOException | InterruptedException e) {
      log.log(Level.SEVERE, e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
