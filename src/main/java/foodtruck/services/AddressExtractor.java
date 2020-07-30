package foodtruck.services;

import java.util.List;

public interface AddressExtractor {
  List<String> parse(String text, String truckId);
}
