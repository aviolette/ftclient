package foodtruck.util;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class Clocks {
  public static final ZoneId CHICAGO = ZoneId.of("America/Chicago");

  public static final ZonedDateTime now(Clock clock) {
    return ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
  }

  public static final Date zonedToDate(ZonedDateTime dt) {
    if (dt == null) {
      return null;
    }
    return new Date(dt.toInstant().toEpochMilli());
  }

  public static Day day(Clock clock) {
    var dt = ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
    return Objects.requireNonNull(Day.fromConstant(dt.getDayOfWeek()))
        .orElseThrow();
  }
}
