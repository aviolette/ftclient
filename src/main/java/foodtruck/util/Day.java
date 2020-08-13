package foodtruck.util;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Optional;


/**
 * An enum for each day of the week.
 * @author aviolette@gmail.com
 * @since Jul 14, 2011
 */
public enum Day {
  monday(DayOfWeek.MONDAY, "mon|monday"),
  tuesday(DayOfWeek.TUESDAY, "tue|tu|tues|tuesday"),
  wednesday(DayOfWeek.WEDNESDAY, "wed|weds|wednesday"),
  thursday(DayOfWeek.THURSDAY, "thu|th|thursday|thurs"),
  friday(DayOfWeek.FRIDAY, "fri|friday"),
  saturday(DayOfWeek.SATURDAY, "sat|saturday"),
  sunday(DayOfWeek.SUNDAY, "su|sunday");

  private final String matchPattern;
  private DayOfWeek isoConstant;

  Day(DayOfWeek dateConstant, String matchPattern) {
    isoConstant = dateConstant;
    this.matchPattern = matchPattern;
  }

  public static Optional<Day> fromConstant(DayOfWeek dayOfWeek) {
    return Arrays.stream(values())
        .filter(dow ->  dow.isoConstant == dayOfWeek )
        .findFirst();
  }

  public boolean isWeekend() {
    return isoConstant == DayOfWeek.SATURDAY ||
        isoConstant == DayOfWeek.SUNDAY;
  }

  public DayOfWeek getConstant() {
    return isoConstant;
  }

  public String getMatchPattern() {
    return matchPattern;
  }
}
