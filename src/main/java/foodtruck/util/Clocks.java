package foodtruck.util;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Clocks {
  public static final ZoneId CHICAGO = ZoneId.of("America/Chicago");

  public static final ZonedDateTime now(Clock clock) {
    return ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
  }
}
