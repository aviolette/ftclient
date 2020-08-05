package foodtruck.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import foodtruck.json.Json;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author andrew@andrewviolette.net
 * @since 8/5/20
 */
public class TruckTest {
  @Test
  public void parseTest() throws JsonProcessingException {
    ObjectMapper mapper = Json.provideObjectMapper();
    var truck = mapper.readValue("{\"id\":\"thecajuncon\",\"name\":\"The Cajun Connoisseur\",\"twitterHandle\":\"thecajuncon\",\"url\":\"http://www.thecajunconnoisseur.com/\",\"categories\":[\"Lunch\",\"Cajun\"],\"description\":\"The Cajun Connoisseur serves Cajun po-boys and other cajun cuisine.\",\"twittalyzer\":true,\"defaultCity\":\"Chicago, IL\",\"facebook\":\"/profile.php?id=100009343451328\",\"donotMatchIf\":\"popup\",\"inactive\":false,\"email\":\"\",\"phone\":\"773-318-1439\",\"stats\":{\"lastUpdated\":\"2018-10-26T05:15:06.022Z\",\"lastSeen\":\"2020-08-04T19:00:00.000Z\",\"firstSeen\":\"2015-09-18T16:00:00.000Z\",\"totalStops\":695,\"stopsThisYear\":98,\"whereLastSeen\":{\"name\":\"35th and Michigan, Chicago, IL\",\"valid\":true,\"eventSpecific\":false,\"radius\":0.0,\"popular\":false,\"justResolved\":false,\"autocomplete\":false,\"designatedStop\":false,\"hasBooze\":false,\"radiateTo\":0,\"closed\":false,\"managerEmails\":[],\"alexaProvided\":false,\"blacklistedFromCalendarSearch\":false,\"latitude\":41.831022999999995,\"longitude\":-87.62323699999999},\"whereFirstSeen\":{\"name\":\"Clark and Monroe, Chicago, IL\",\"valid\":true,\"eventSpecific\":false,\"radius\":0.0,\"popular\":false,\"justResolved\":false,\"autocomplete\":false,\"designatedStop\":false,\"hasBooze\":false,\"radiateTo\":0,\"closed\":false,\"managerEmails\":[],\"alexaProvided\":false,\"blacklistedFromCalendarSearch\":false,\"latitude\":41.880306,\"longitude\":-87.630845},\"numberOfDaysOutThisYear\":0},\"hidden\":false,\"beaconnaiseEmails\":[],\"previewIcon\":\"https://storage.googleapis.com/truckicons/tcc_preview.png\",\"displayEmailPublicly\":true,\"instagramId\":\"\",\"fleetSize\":0,\"menuUrl\":\"\",\"blacklistLocationNames\":[],\"phoneticAliases\":[\"the kitchen connoisseur\"],\"neverLinkTwitter\":false,\"postDailySchedule\":false,\"postWeeklySchedule\":false,\"postAtNewStop\":false,\"notifyOfLocationChanges\":false,\"disableBeaconsUntilLunchtime\":false,\"notifyWhenLeaving\":false,\"notifyWhenDeviceIssues\":false,\"drupalCalendar\":\"\",\"hasTwitterCredentials\":false}", Truck.class);

    assertThat(truck).isNotNull();
    assertThat(truck.isUsingTwittalyzer()).isTrue();
  }
}
