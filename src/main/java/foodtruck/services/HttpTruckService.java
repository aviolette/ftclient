package foodtruck.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import foodtruck.json.Json;
import foodtruck.models.Truck;

public class HttpTruckService implements TruckService {

  private static final Logger log = Logger.getLogger(HttpTruckService.class.getName());
  private final ObjectMapper mapper;

  public HttpTruckService() {
    mapper = Json.provideObjectMapper();
  }

  @Override
  public List<Truck> findTrucksWithGoogleCalendars() {
    return findTrucks("google_calendar");
  }

  @Override
  public List<Truck> findTrucksWithICalCalendars() {
    return findTrucks("ical");
  }

  @Override
  public Optional<Truck> findById(String truckId) {
    return httpGet("/services/v2/trucks/" + truckId, (response) -> {
      try {
        if (response.statusCode() == 404) {
          return Optional.empty();
        } else if (response.statusCode() >= 400) {
          handleResponseError(response);
        }
        Truck truck = mapper.readValue(response.body(), Truck.class);
        return Optional.of(truck);
      } catch (JsonProcessingException e) {
        return Optional.empty();
      }
    });
  }

  private void handleResponseError(HttpResponse<String> response) {
    log.log(Level.SEVERE, "Response code: " + response.statusCode());
    log.log(Level.SEVERE, response.body());
    throw new RuntimeException(response.toString());
  }

  private List<Truck> findTrucks(String filterParam) {
    return httpGet("/services/v2/trucks?filter=" + filterParam, (response) -> {
      try {
        if (response.statusCode() >= 400) {
          handleResponseError(response);
        }
        return Arrays.asList(mapper.readValue(response.body(), Truck[].class));
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private static <T> T httpGet(String uri, Function<HttpResponse<String>, T> responseFunction) {
    var client = HttpClient.newHttpClient();
    try {
      var request = java.net.http.HttpRequest.newBuilder()
          .uri(URI.create("https://www.chicagofoodtruckfinder.com" + uri))
          .header("Content-Type", "application/json")
          .header("x-ftf-secret", System.getenv().get("FOODTRUCK_API_SECRET"))
          .GET()
          .build();
      var documentResponse = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
      return responseFunction.apply(documentResponse);
    } catch (IOException | InterruptedException e) {
      log.log(Level.SEVERE, e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }
}
