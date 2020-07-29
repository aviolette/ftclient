package foodtruck.pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.batching.BatchingSettings;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import org.threeten.bp.Duration;

import foodtruck.json.Json;

public class PubSubPublisher implements MessagePublisher {

  private static final Logger log = Logger.getLogger(PubSubPublisher.class.getName());

  @Override
  public void publish(List<?> things, String topicName) throws Exception {
    if (things.isEmpty()) {
      return;
    }
    var publisher = Publisher.newBuilder(TopicName.of("chicagofoodtrucklocator", topicName)).setBatchingSettings(
        BatchingSettings.newBuilder()
            .setElementCountThreshold((long) things.size())
            .setRequestByteThreshold(5000L)
            .setDelayThreshold(Duration.ofMillis(100))
            .build()).build();
    var futures = new ArrayList<ApiFuture<String>>(things.size());
    try {
      var mapper = Json.provideObjectMapper();
      for (var thing : things) {
        String message = mapper.writeValueAsString(thing);
        log.fine(String.format("Message: %s", message));
        ByteString data = ByteString.copyFromUtf8(message);
        futures.add(publisher.publish(PubsubMessage.newBuilder().setData(data).build()));
      }
    } finally {
      ApiFutures.allAsList(futures).get();
      log.log(Level.INFO, "All messages published");

      publisher.shutdown();
      publisher.awaitTermination(1, TimeUnit.MINUTES);
    }
  }
}
