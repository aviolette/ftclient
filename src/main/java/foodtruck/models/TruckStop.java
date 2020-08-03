package foodtruck.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Specifies an truck at a location at a date and time.
 * @author aviolette@gmail.com
 * @since Jul 12, 2011
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TruckStop implements Serializable {
  private static final Logger log = Logger.getLogger(TruckStop.class.getName());
  private Truck truck;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;
  private Location location;
  private boolean locked;
  @Nullable
  private ZonedDateTime lastUpdated;
  private @Nullable ZonedDateTime fromBeacon;
  private @Nullable ZonedDateTime manuallyUpdated;
  private List<String> notes;
  private StopOrigin origin;
  private @Nullable Long createdWithDeviceId;
  private @Nullable String description;
  private @Nullable String imageUrl;
  private @Nullable String url;

  @Nullable
  public String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }


  public TruckStop() {}

  private TruckStop(Builder builder) {
    truck = builder.truck;
    startTime = builder.startTime;
    endTime = builder.endTime;
    location = builder.location;
    locked = builder.locked;
    fromBeacon = builder.fromBeacon;
    lastUpdated = builder.lastUpdated;
    notes = ImmutableList.copyOf(builder.notes);
    origin = builder.origin;
    createdWithDeviceId = builder.createdWithDeviceId;
    manuallyUpdated = builder.manuallyUpdated;
    description = builder.description;
    imageUrl = builder.imageUrl;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  @Nullable
  public String getImageUrl() {
    return imageUrl;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(TruckStop stop) {
    return new Builder(stop);
  }

  @Nullable
  public ZonedDateTime getManuallyUpdated() {
    return manuallyUpdated;
  }

  public Long getCreatedWithDeviceId() {
    return createdWithDeviceId;
  }

  public StopOrigin getOrigin() {
    return origin;
  }

  public List<String> getNotes() {
    return notes;
  }

  public boolean isLocked() {
    return locked;
  }

  public Truck getTruck() {
    return truck;
  }

  /**
   * Returns the time the stop was last automatically updated (could be null if it was only manually entered).
   */
  @Nullable
  public ZonedDateTime getLastUpdated() {
    return lastUpdated;
  }

  public ZonedDateTime getStartTime() {
    return startTime;
  }

  public ZonedDateTime getEndTime() {
    return endTime;
  }

  public Location getLocation() {
    return location;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(truck, startTime, endTime, location);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (o == null) {
      return false;
    } else if (!(o instanceof TruckStop)) {
      return false;
    }
    TruckStop obj = (TruckStop) o;
    return truck.equals(obj.truck) && startTime.equals(obj.startTime) &&
        endTime.equals(obj.endTime) &&
        location.equals(obj.location);
  }

  @Override
  public String toString() {
    try {
      return MoreObjects.toStringHelper(this)
          .add("truck", truck.getId())
          .add("startTime", startTime)
          .add("endTime", endTime)
          .add("lastUpdated", lastUpdated)
          .add("location", location)
          .add("description", description)
          .add("image URL", imageUrl)
          .add("createdWithDevice", createdWithDeviceId)
          .toString();
    } catch (Throwable t) {
      log.log(Level.WARNING, t.getMessage(), t);
      return truck.getId();
    }
  }

  /**
   * Returns a new TruckStop with a new startTime
   */
  public TruckStop withStartTime(ZonedDateTime startTime) {
    return TruckStop.builder(this)
        .startTime(startTime)
        .build();
  }

  /**
   * Returns a new TruckStop with a new endTime
   */
  public TruckStop withEndTime(ZonedDateTime endTime) {
    return TruckStop.builder(this)
        .endTime(endTime)
        .build();
  }

  public boolean activeDuring(ZonedDateTime dateTime) {
    return startTime.equals(dateTime) || (dateTime.isAfter(startTime) && dateTime.isBefore(endTime));
  }

  public boolean isActiveNow() {
    return activeDuring(startTime);
  }

  public TruckStop withLocation(Location location) {
    return TruckStop.builder(this)
        .location(location)
        .build();
  }

  /**
   * Returns true if the stop has expired by the specified time.
   */
  public boolean hasExpiredBy(ZonedDateTime currentTime) {
    return endTime.isBefore(currentTime);
  }

  @Nullable
  public ZonedDateTime getBeaconTime() {
    return fromBeacon;
  }

  public boolean isFromBeacon() {
    return fromBeacon != null;
  }

  public boolean isVendorStop() {
    return getOrigin() == StopOrigin.VENDORCAL;
  }

  public static class Builder {

    private Truck truck;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Location location;
    private boolean locked;
    private @Nullable ZonedDateTime fromBeacon;
    private @Nullable Long key;
    private @Nullable ZonedDateTime lastUpdated;
    private List<String> notes = Lists.newLinkedList();
    private StopOrigin origin = StopOrigin.UNKNOWN;
    private @Nullable Long createdWithDeviceId;
    private @Nullable ZonedDateTime manuallyUpdated;
    private String description;
    private String imageUrl;

    private Builder() {
    }

    private Builder(TruckStop stop) {
      truck = stop.getTruck();
      startTime = stop.getStartTime();
      endTime = stop.getEndTime();
      location = stop.getLocation();
      locked = stop.isLocked();
      fromBeacon = stop.getBeaconTime();
      lastUpdated = stop.getLastUpdated();
      notes = Lists.newLinkedList(stop.getNotes());
      origin = stop.getOrigin();
      createdWithDeviceId = stop.createdWithDeviceId;
      manuallyUpdated = stop.manuallyUpdated;
      description = stop.description;
      imageUrl = stop.imageUrl;
    }

    public Builder createdWithDeviceId(Long deviceId) {
      this.createdWithDeviceId = deviceId;
      return this;
    }

    public Builder origin(StopOrigin origin) {
      this.origin = origin;
      return this;
    }

    public Builder notes(List<String> notes) {
      this.notes = Lists.newLinkedList(notes);
      return this;
    }

    public Builder appendNote(String note) {
      this.notes.add(note);
      return this;
    }

    public Builder key(@Nullable Long key) {
      this.key = key;
      return this;
    }

    public Builder truck(Truck truck) {
      this.truck = truck;
      return this;
    }

    public Builder startTime(ZonedDateTime dateTime) {
      this.startTime = dateTime;
      return this;
    }

    public Builder manuallyUpdated(@Nullable ZonedDateTime manuallyUpdated) {
      this.manuallyUpdated = manuallyUpdated;
      return this;
    }

    public Builder lastUpdated(@Nullable ZonedDateTime lastUpdated) {
      this.lastUpdated = lastUpdated;
      return this;
    }

    public Builder endTime(ZonedDateTime endTime) {
      this.endTime = endTime;
      return this;
    }

    public Builder location(Location location) {
      this.location = location;
      return this;
    }

    public Builder locked(boolean locked) {
      this.locked = locked;
      return this;
    }

    public Builder fromBeacon(@Nullable ZonedDateTime fromBeacon) {
      this.fromBeacon = fromBeacon;
      return this;
    }

    public Builder imageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public TruckStop build() {
      return new TruckStop(this);
    }

    public Builder prependNotes(List<String> notes) {
      LinkedList<String> newNotes = Lists.newLinkedList(notes);
      newNotes.addAll(this.notes);
      this.notes = newNotes;
      return this;
    }

    public ZonedDateTime startTime() {
      return startTime;
    }

    public boolean hasTimes() {
      return startTime != null && endTime != null;
    }

    public ZonedDateTime endTime() {
      return endTime;
    }

    public boolean hasCategory(String category) {
      return (truck != null && truck.getCategories()
          .contains(category));
    }

  }
}
