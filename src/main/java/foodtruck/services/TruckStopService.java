package foodtruck.services;

import java.time.LocalDate;
import java.util.List;

import foodtruck.models.TruckStop;

/**
 * @author andrew@andrewviolette.net
 * @since 7/31/20
 */
public interface TruckStopService {
  List<TruckStop> findDuring(String truckId, LocalDate localDate);
}
