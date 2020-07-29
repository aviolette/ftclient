package foodtruck.services;

import java.util.List;
import java.util.Optional;

import foodtruck.models.Truck;

public interface TruckService {

  List<Truck> findTrucksWithGoogleCalendars();

  List<Truck> findTrucksWithICalCalendars();

  List<Truck> findByTwitterHandle(String twitterHandle);

  Optional<Truck> findById(String truckId);
}
