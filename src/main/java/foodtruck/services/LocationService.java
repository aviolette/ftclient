package foodtruck.services;

import java.util.Optional;

import foodtruck.model.Location;

public interface LocationService {
  Optional<Location> findById(long id);

  Optional<Location> findByName(String name);
}
