package foodtruck.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import foodtruck.json.Json;

/**
 * @author andrew@andrewviolette.net
 * @since 8/4/20
 */
public class LocationTest {

  @Test
  public void parseTest() throws JsonProcessingException {
    ObjectMapper mapper = Json.provideObjectMapper();
    String value = "\n" + "{\n" + "  \"latitude\": 41.868449999999996,\n" + "  \"longitude\": -87.632487,\n" +
        "  \"name\": \"Roosevelt Collection\",\n" + "  \"valid\": true,\n" +
        "  \"description\": \"150 West Roosevelt Road, Chicago, IL\",\n" +
        "  \"url\": \"http://www.rooseveltcollection.com/\",\n" + "  \"radius\": 0,\n" + "  \"popular\": false,\n" +
        "  \"justResolved\": false,\n" + "  \"autocomplete\": true,\n" + "  \"alias\": \"\",\n" +
        "  \"twitterHandle\": \"rclofts\",\n" + "  \"designatedStop\": false,\n" + "  \"hasBooze\": false,\n" +
        "  \"ownedBy\": null,\n" + "  \"radiateTo\": 0,\n" + "  \"phoneNumber\": null,\n" + "  \"email\": \"\",\n" +
        "  \"facebookUri\": \"\",\n" + "  \"closed\": false,\n" + "  \"eventCalendarUrl\": \"\",\n" +
        "  \"alexaProvided\": false,\n" + "  \"createdBy\": null,\n" + "  \"blacklistedFromCalendarSearch\": false,\n" +
        "  \"city\": \"Chicago\",\n" + "  \"neighborhood\": \"Chicago Loop\",\n" +
        "  \"imageUrl\": \"http://storage.googleapis.com/cftf_locationicons/5797982880399360-1550006040595.jpg\",\n" +
        "  \"resolved\": true,\n" + "  \"event\": false,\n" + "  \"shortenedName\": \"Roosevelt Collection\"\n" + "}";
    var location = mapper.readValue(value, Location.class);
  }

  @Test
  public void parseTest2() throws JsonProcessingException {
    ObjectMapper mapper = Json.provideObjectMapper();
    String value = "[{\"key\":5797982880399360,\"name\":\"Roosevelt Collection\",\"valid\":true,\"description\":\"150 West Roosevelt Road, Chicago, IL\",\"url\":\"http://www.rooseveltcollection.com/\",\"eventSpecific\":false,\"radius\":0.0,\"popular\":false,\"justResolved\":false,\"autocomplete\":true,\"alias\":\"\",\"twitterHandle\":\"rclofts\",\"designatedStop\":false,\"hasBooze\":false,\"radiateTo\":0,\"phoneNumber\":\"\",\"email\":\"\",\"facebookUri\":\"\",\"closed\":false,\"imageUrl\":\"http://storage.googleapis.com/cftf_locationicons/5797982880399360-1550006040595.jpg\",\"eventCalendarUrl\":\"\",\"managerEmails\":[],\"alexaProvided\":false,\"blacklistedFromCalendarSearch\":false,\"city\":\"Chicago\",\"neighborhood\":\"Chicago Loop\",\"latitude\":41.868449999999996,\"longitude\":-87.632487}]";
    var locations = mapper.readValue(value, Location[].class);
  }
}
