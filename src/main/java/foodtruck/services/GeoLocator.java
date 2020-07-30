package foodtruck.services;

import java.util.Optional;

import foodtruck.models.Location;

public interface GeoLocator {
  Optional<Location> broadSearch(String name);

}
