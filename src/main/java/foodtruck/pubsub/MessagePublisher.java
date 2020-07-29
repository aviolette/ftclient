package foodtruck.pubsub;

import java.util.List;

public interface MessagePublisher {
  void publish(List<?> items, String topic) throws Exception;
}
