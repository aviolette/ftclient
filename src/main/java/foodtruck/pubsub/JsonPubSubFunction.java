package foodtruck.pubsub;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.cloud.functions.Context;
import com.google.cloud.functions.RawBackgroundFunction;

import foodtruck.json.Json;

/**
 * @author andrew@andrewviolette.net
 * @since 8/9/20
 */
public abstract class JsonPubSubFunction<T> implements RawBackgroundFunction {

  private Class<T> readType;

  public JsonPubSubFunction(Class<T> readType) {
    this.readType = readType;
  }

  @Override
  public final void accept(String s, Context context) throws Exception {
    var mapper = Json.provideObjectMapper();
    var tree = mapper.readTree(s);
    var data = tree.get("data")
        .asText();
    var payload = new String(Base64.getDecoder()
        .decode(data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    var theObject = mapper.readValue(payload, readType);
    execute(theObject, context);
  }

  protected abstract void execute(T theObject, Context context);
}
