package foodtruck.services;

import java.util.Optional;

import foodtruck.models.Location;

public interface LocationService {

  Optional<Location> findById(long id);

  Optional<Location> findByName(String name);

  Optional<Location> findByAlias(String alias);
}
