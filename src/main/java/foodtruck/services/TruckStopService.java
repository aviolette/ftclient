package foodtruck.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import foodtruck.models.Location;
import foodtruck.models.Truck;
import foodtruck.models.TruckStop;

/**
 * @author andrew@andrewviolette.net
 * @since 7/31/20
 */
public interface TruckStopService {
  List<TruckStop> findDuring(String truckId, LocalDate localDate);

  Set<Truck> findNearLocation(Location location, Instant instant);
}
