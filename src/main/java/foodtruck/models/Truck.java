package foodtruck.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

/**
 * Static information about a food truck.
 * @author aviolette@gmail.com
 * @since Jul 12, 2011
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Truck implements Serializable {

  private static final Joiner BLACKLIST_JOINER = Joiner.on("; ");
  private String id;
  private String name;
  private String twitterHandle;
  private String url;
  private String iconUrl;
  private Set<String> categories;
  private String description;
  private boolean twittalyzer;
  private String defaultCity;
  private String facebook;
  private Pattern matchOnlyIf;
  private Pattern donotMatchIf;
  private boolean inactive;
  private @Nullable String calendarUrl;
  private @Nullable String email;
  private @Nullable String phone;
  private String yelpSlug;
  private @Nullable String facebookPageId;
  private boolean hidden;
  private Set<String> beaconnaiseEmails;
  private @Nullable String previewIcon;
  private boolean displayEmailPublicly;
  private String instagramId;
  private @Nullable String fullsizeImage;
  private int timezoneAdjustment;
  private int fleetSize;
  private String backgroundImage;
  private String backgroundImageLarge;
  private @Nullable String menuUrl;
  private List<String> blacklistLocationNames;
  private @Nullable String phoneticMarkup;
  private List<String> phoneticAliases;
  private @Nullable String twitterToken;
  private @Nullable String twitterTokenSecret;
  private boolean neverLinkTwitter;
  private boolean postDailySchedule;
  private boolean postWeeklySchedule;
  private boolean postAtNewStop;
  private boolean notifyOfLocationChanges;
  private boolean disableBeaconsUntilLunchtime;
  private boolean notifyWhenLeaving;
  private boolean notifyWhenDeviceIssues;
  private @Nullable String drupalCalendar;
  private @Nullable String icalCalendar;
  private @Nullable String squarespaceCalendar;

  // For serialization (for storage in memcached)
  private Truck() {
  }

  private Truck(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.twitterHandle = builder.twitter;
    this.url = builder.url;
    this.iconUrl = builder.iconUrl;
    this.categories = Collections.unmodifiableSet(builder.categories);
    this.description = builder.description;
    this.twittalyzer = builder.twittalyzer;
    this.defaultCity = builder.defaultCity;
    this.facebook = builder.facebook;
    this.facebookPageId = builder.facebookPageId;
    this.matchOnlyIf = builder.matchOnlyIf;
    this.inactive = builder.inactive;
    this.calendarUrl = builder.calendarUrl;
    this.donotMatchIf = builder.donotMatchIf;
    this.email = builder.email;
    this.phone = builder.phone;
    this.yelpSlug = builder.yelpSlug;
    this.hidden = builder.hidden;
    this.beaconnaiseEmails = builder.beaconnaiseEmails;
    this.previewIcon = builder.previewIcon;
    this.displayEmailPublicly = builder.displayEmailPublicly;
    this.instagramId = builder.instagramId;
    this.fullsizeImage = builder.fullsizeImage;
    this.timezoneAdjustment = builder.timezoneAdjustment;
    this.fleetSize = builder.fleetSize;
    this.backgroundImage = builder.backgroundImage;
    this.backgroundImageLarge = builder.backgroundImageLarge;
    this.menuUrl = builder.menuUrl;
    this.blacklistLocationNames = builder.blacklistLocationNames;
    this.phoneticMarkup = builder.phoneticMarkup;
    this.phoneticAliases = builder.phoneticAliases;
    this.twitterToken = builder.twitterToken;
    this.twitterTokenSecret = builder.twitterTokenSecret;
    this.neverLinkTwitter = builder.neverLinkTwitter;
    this.postDailySchedule = builder.postDailySchedule;
    this.postWeeklySchedule = builder.postWeeklySchedule;
    this.postAtNewStop = builder.postAtNewStop;
    this.notifyOfLocationChanges = builder.notifyOfLocationChanges;
    this.disableBeaconsUntilLunchtime = builder.disableBeaconsUntilLunchtime;
    this.notifyWhenDeviceIssues = builder.notifyWhenDeviceIssues;
    this.notifyWhenLeaving = builder.notifyWhenLeaving;
    this.drupalCalendar = builder.drupalCalendar;
    this.icalCalendar = builder.icalCalendar;
    this.squarespaceCalendar = builder.squarespaceCalendar;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(Truck t) {
    return new Builder(t);
  }

  public static String canonize(String name) {
    name = name.toLowerCase();
    if (name.startsWith("the ")) {
      name = name.substring(4);
    }
    return name;
  }

  public Builder append() {
    return new Builder(this);
  }

  public boolean isNotifyWhenLeaving() {
    return notifyWhenLeaving;
  }

  public boolean isNotifyWhenDeviceIssues() {
    return notifyWhenDeviceIssues;
  }

  public boolean isNotifyOfLocationChanges() {
    return notifyOfLocationChanges;
  }

  @JsonIgnore
  @Nullable
  public String getTwitterToken() {
    return twitterToken;
  }

  @JsonIgnore
  @Nullable
  public String getTwitterTokenSecret() {
    return twitterTokenSecret;
  }

  public boolean isNeverLinkTwitter() {
    return neverLinkTwitter;
  }

  public boolean isPostDailySchedule() {
    return postDailySchedule;
  }

  public boolean isPostWeeklySchedule() {
    return postWeeklySchedule;
  }

  public boolean isMatchesMorningStops() {
    return categories.contains("Breakfast");
  }

  public boolean isPostAtNewStop() {
    return postAtNewStop;
  }

  public boolean getHasSocialMediaCredentials() {
    return getHasTwitterCredentials();
  }

  @JsonProperty
  public boolean getHasTwitterCredentials() {
    return !(Strings.isNullOrEmpty(twitterToken) || Strings.isNullOrEmpty(twitterTokenSecret));
  }

  public List<String> getPhoneticAliases() {
    return phoneticAliases;
  }

  public String getPhoneticAliasCSV() {
    return Joiner.on(", ")
        .join(phoneticAliases);
  }

  @Nullable
  public String getPhoneticMarkup() {
    return phoneticMarkup;
  }

  public String getNameInSSML() {
    return MoreObjects.firstNonNull(phoneticMarkup, name);
  }

  @Nullable
  public String getIcalCalendar() {
    return icalCalendar;
  }

  @Nullable
  public String getSquarespaceCalendar() {
    return squarespaceCalendar;
  }

  @Nullable
  public String getDrupalCalendar() {
    return drupalCalendar;
  }

  public List<String> getBlacklistLocationNames() {
    return blacklistLocationNames;
  }

  public String getBlacklistLocationsList() {
    return BLACKLIST_JOINER.join(blacklistLocationNames);
  }

  @Nullable
  public String getMenuUrl() {
    return menuUrl;
  }

  @Deprecated
  @JsonIgnore
  @Nullable
  public String getFullsizeImage() {
    return fullsizeImage;
  }

  @Nullable
  public String getInstagramId() {
    return instagramId;
  }

  public boolean isDisplayEmailPublicly() {
    return this.displayEmailPublicly;
  }

  public boolean isVisible() {
    return !isHidden();
  }

  public boolean isHidden() {
    return hidden;
  }

  public String getDefaultCity() {
    return defaultCity;
  }

  @Nullable
  public String getPreviewIcon() {
    return previewIcon;
  }

  public String getName() {
    return name;
  }

  @Deprecated
  @JsonIgnore
  public String getYelpSlug() {
    return yelpSlug;
  }

  public boolean isPopupVendor() {
    return categories.contains("Popup");
  }

  @Nullable
  public String getCalendarUrl() {
    return calendarUrl;
  }

  @SuppressWarnings("UnusedDeclaration")
  public String getCategoryList() {
    return Joiner.on(", ")
        .join(categories);
  }

  public String getPhoneticAliasesList() {
    return Joiner.on(", ")
        .join(phoneticAliases);
  }

  public Set<String> getCategories() {
    return categories;
  }

  public Set<String> getBeaconnaiseEmails() {
    return beaconnaiseEmails;
  }

  @SuppressWarnings("UnusedDeclaration")
  public String getBeaconnaiseList() {
    return Joiner.on(", ")
        .join(beaconnaiseEmails);
  }

  public @Nullable
  String getDescription() {
    return description;
  }

  public String getTwitterHandle() {
    return twitterHandle;
  }

  public String getUrl() {
    return url;
  }


  public String getId() {
    return id;
  }

  public boolean isUsingTwittalyzer() {
    return twittalyzer;
  }

  @Nullable
  public String getFacebook() {
    return facebook;
  }

  @Nullable
  public String getFacebookHandle() {
    if (facebook == null || facebook.lastIndexOf('/') != 0) {
      return facebook;
    } else {
      return "@" + facebook.substring(1);
    }
  }

  @Deprecated
  @JsonIgnore
  @Nullable
  public String getFacebookPageId() {
    return facebookPageId;
  }

  public boolean isInactive() {
    return inactive;
  }

  @Nullable
  public String getPublicEmail() {
    return displayEmailPublicly ? getEmail() : null;
  }

  @Nullable
  public String getEmail() {
    return email;
  }

  @Nullable
  public String getPhone() {
    return phone;
  }

  public boolean shouldAnalyzeStories() {
    return !Strings.isNullOrEmpty(twitterHandle) || !Strings.isNullOrEmpty(facebook);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, url, iconUrl, twitterHandle, inactive);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Truck)) {
      return false;
    }
    Truck truck = (Truck) o;
    return id.equals(truck.id) && name.equals(truck.name) && Objects.equal(iconUrl, truck.iconUrl) &&
        Objects.equal(twitterHandle, truck.twitterHandle) && Objects.equal(url, truck.url) &&
        inactive == truck.inactive;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("url", url)
        .add("phone", phone)
        .add("iconUrl", iconUrl)
        .add("twitterHandle", twitterHandle)
        .add("uses twittalyzer", twittalyzer)
        .add("facebook URI", facebook)
        .add("Yelp Slug", yelpSlug)
        .add("inactive", inactive)
        .add("instagramId", instagramId)
        .toString();
  }

  @Nullable
  public String getMatchOnlyIfString() {
    return matchOnlyIf == null ? null : matchOnlyIf.toString();
  }

  @Nullable
  public Pattern getMatchOnlyIf() {
    return matchOnlyIf;
  }

  @Nullable
  public String getDonotMatchIfString() {
    return donotMatchIf == null ? null : donotMatchIf.toString();
  }

  public @Nullable
  Pattern getDonotMatchIf() {
    return donotMatchIf;
  }


  public boolean match(String tweet) {
    Pattern p = getMatchOnlyIf();
    if (p != null) {
      Matcher m = p.matcher(tweet.toLowerCase());
      return m.find();
    }
    p = getDonotMatchIf();
    if (p != null) {
      Matcher m = p.matcher(tweet.toLowerCase());
      return !m.find();
    }
    return true;
  }

  public Set<String> getPublicCategories() {
    return publicCategories();
  }

  public Set<String> publicCategories() {
    return categories.stream()
        .filter(input -> !(input.equals("Lunch") || input.equals("HasStore") || input.equals("1HRStops") ||
            input.equals("MorningSquatter") || input.equals("AssumeNoTimeEqualsLunch") || input.equals("Chicago") ||
            input.equals("Burbs")))
        .collect(Collectors.toSet());
  }

  public int getFleetSize() {
    return fleetSize;
  }

  public String canonicalName() {
    return canonize(getName());
  }

  public boolean getDeriveStopsFromSocialMedia() {
    return isUsingTwittalyzer();
  }

  public String nameForTwitterDisplay() {
    if (Strings.isNullOrEmpty(twitterHandle)) {
      return name;
    }
    return "@" + twitterHandle;
  }

  public static class Builder {

    public Set<String> categories = Set.of();
    public String description;
    public Set<String> beaconnaiseEmails = Set.of();
    public boolean disableBeaconsUntilLunchtime;
    private String id;
    private String name;
    private @Nullable String url;
    private String iconUrl = "https://storage.googleapis.com/truckpreviews/truck_holder.svg";
    private @Nullable String twitter;
    private boolean twittalyzer;
    private String defaultCity = "Chicago, IL";
    private String facebook;
    private Pattern matchOnlyIf;
    private boolean inactive;
    private @Nullable String calendarUrl;
    private Pattern donotMatchIf;
    private @Nullable String email;
    private @Nullable String phone;
    private @Nullable String yelpSlug;
    private @Nullable String facebookPageId;
    private boolean hidden;
    private @Nullable String previewIcon;
    private boolean displayEmailPublicly;
    private @Nullable String instagramId;
    private @Nullable String fullsizeImage;
    private int timezoneAdjustment = 0;
    private boolean scanFacebook;
    private String lastScanned;
    private int fleetSize;
    private String backgroundImage;
    private String backgroundImageLarge;
    private @Nullable String menuUrl;
    private List<String> blacklistLocationNames = List.of();
    private @Nullable String phoneticMarkup;
    private List<String> phoneticAliases = List.of();
    private @Nullable String twitterToken;
    private @Nullable String twitterTokenSecret;
    private boolean neverLinkTwitter;
    private boolean postDailySchedule;
    private boolean postWeeklySchedule;
    private boolean postAtNewStop;
    private boolean notifyOfLocationChanges;
    private boolean notifyWhenLeaving;
    private boolean notifyWhenDeviceIssues;
    private @Nullable String drupalCalendar;
    private @Nullable String icalCalendar;
    private @Nullable String squarespaceCalendar;

    public Builder() {
    }

    public Builder(Truck truck) {
      this.id = truck.id;
      this.name = truck.name;
      this.url = truck.url;
      this.iconUrl = truck.iconUrl;
      this.twitter = truck.twitterHandle;
      this.categories = truck.categories;
      this.description = truck.description;
      this.twittalyzer = truck.twittalyzer;
      this.defaultCity = truck.defaultCity;
      this.facebook = truck.facebook;
      this.donotMatchIf = truck.donotMatchIf;
      this.matchOnlyIf = truck.matchOnlyIf;
      this.inactive = truck.inactive;
      this.calendarUrl = truck.calendarUrl;
      this.yelpSlug = truck.yelpSlug;
      this.facebookPageId = truck.facebookPageId;
      this.hidden = truck.hidden;
      this.phone = truck.phone;
      this.email = truck.email;
      this.previewIcon = truck.previewIcon;
      this.beaconnaiseEmails = truck.beaconnaiseEmails;
      this.displayEmailPublicly = truck.displayEmailPublicly;
      this.instagramId = truck.instagramId;
      this.fullsizeImage = truck.fullsizeImage;
      this.timezoneAdjustment = truck.timezoneAdjustment;
      this.fleetSize = truck.fleetSize;
      this.backgroundImage = truck.backgroundImage;
      this.backgroundImageLarge = truck.backgroundImageLarge;
      this.menuUrl = truck.menuUrl;
      this.blacklistLocationNames = truck.blacklistLocationNames;
      this.phoneticMarkup = truck.phoneticMarkup;
      this.phoneticAliases = truck.phoneticAliases;
      this.twitterToken = truck.twitterToken;
      this.twitterTokenSecret = truck.twitterTokenSecret;
      this.neverLinkTwitter = truck.neverLinkTwitter;
      this.postDailySchedule = truck.postDailySchedule;
      this.postWeeklySchedule = truck.postWeeklySchedule;
      this.postAtNewStop = truck.postAtNewStop;
      this.notifyOfLocationChanges = truck.notifyOfLocationChanges;
      this.disableBeaconsUntilLunchtime = truck.disableBeaconsUntilLunchtime;
      this.notifyWhenDeviceIssues = truck.notifyWhenDeviceIssues;
      this.notifyWhenLeaving = truck.notifyWhenLeaving;
      this.drupalCalendar = truck.drupalCalendar;
      this.icalCalendar = truck.icalCalendar;
      this.squarespaceCalendar = truck.squarespaceCalendar;
    }

    public Builder squarespaceCalendar(@Nullable String squarespaceCalendar) {
      this.squarespaceCalendar = squarespaceCalendar;
      return this;
    }

    public Builder icalCalendar(@Nullable String icalCalendar) {
      this.icalCalendar = icalCalendar;
      return this;
    }

    public Builder drupalCalendar(@Nullable String drupalCalendar) {
      this.drupalCalendar = drupalCalendar;
      return this;
    }

    public Builder notifyWhenDeviceIssues(boolean notify) {
      this.notifyWhenDeviceIssues = notify;
      return this;
    }

    public Builder notifyWhenLeaving(boolean notify) {
      this.notifyWhenLeaving = notify;
      return this;
    }

    public Builder notifyOfLocationChanges(boolean notify) {
      this.notifyOfLocationChanges = notify;
      return this;
    }

    public Builder phoneticMarkup(String markup) {
      this.phoneticMarkup = markup;
      return this;
    }

    public Builder blacklistLocationNames(List<String> locationNames) {
      this.blacklistLocationNames = locationNames;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Deprecated
    public Builder fullsizeImage(@Nullable String fullsizeImage) {
      this.fullsizeImage = fullsizeImage;
      return this;
    }

    public Builder instagramId(@Nullable String instagramId) {
      this.instagramId = instagramId;
      return this;
    }

    public Builder displayEmailPublicly(boolean displayEmailPublicly) {
      this.displayEmailPublicly = displayEmailPublicly;
      return this;
    }

    public Builder hidden(boolean hidden) {
      this.hidden = hidden;
      return this;
    }

    public Builder lastScanned(String lastScannedId) {
      this.lastScanned = lastScannedId;
      return this;
    }

    public Builder beaconnaiseEmails(Set<String> emails) {
      this.beaconnaiseEmails = emails;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder previewIcon(String previewIcon) {
      this.previewIcon = previewIcon;
      return this;
    }

    public Builder url(@Nullable String url) {
      this.url = url;
      return this;
    }

    public Builder iconUrl(String iconUrl) {
      this.iconUrl = iconUrl;
      return this;
    }

    public Builder categories(Set<String> categories) {
      this.categories = categories;
      return this;
    }

    public Builder matchOnlyIf(@Nullable String regex) {
      if (!Strings.isNullOrEmpty(regex)) {
        this.matchOnlyIf = Pattern.compile(regex);
      } else {
        this.matchOnlyIf = null;
      }
      return this;
    }

    public Builder defaultCity(String defaultCity) {
      this.defaultCity = defaultCity;
      return this;
    }

    public Builder description(@Nullable String description) {
      this.description = description;
      return this;
    }

    public Builder twitterHandle(@Nullable String twitter) {
      this.twitter = twitter;
      return this;
    }

    public Builder useTwittalyzer(boolean twittalyzer) {
      this.twittalyzer = twittalyzer;
      return this;
    }

    public Truck build() {
      return new Truck(this);
    }

    public Builder facebook(@Nullable String facebook) {
      this.facebook = facebook;
      return this;
    }

    public Builder facebookPageId(@Nullable String facebookPageId) {
      this.facebookPageId = facebookPageId;
      return this;
    }

    public Builder calendarUrl(@Nullable String calendarUrl) {
      this.calendarUrl = calendarUrl;
      return this;
    }

    public Builder inactive(Boolean inactive) {
      this.inactive = inactive;
      return this;
    }

    public Builder email(@Nullable String email) {
      this.email = email;
      return this;
    }

    public Builder phone(@Nullable String phone) {
      this.phone = phone;
      return this;
    }

    @Deprecated
    public Builder yelpSlug(@Nullable String yelpSlug) {
      this.yelpSlug = yelpSlug;
      return this;
    }

    public Builder scanFacebook(boolean scanFacebook) {
      this.scanFacebook = scanFacebook;
      return this;
    }

    public Builder donotMatchIf(@Nullable String regex) {
      if (!Strings.isNullOrEmpty(regex)) {
        this.donotMatchIf = Pattern.compile(regex);
      } else {
        this.donotMatchIf = null;
      }
      return this;
    }

    public Builder timezoneOffset(int timezoneAdjustment) {
      this.timezoneAdjustment = timezoneAdjustment;
      return this;
    }

    public Builder fleetSize(int size) {
      this.fleetSize = size;
      return this;
    }

    public Builder phoneticAliases(List<String> phoneticAliases) {
      this.phoneticAliases = phoneticAliases;
      return this;
    }

    public Builder backgroundImage(String image) {
      this.backgroundImage = image;
      return this;
    }

    public Builder backgroundImageLarge(String image) {
      this.backgroundImageLarge = image;
      return this;
    }

    public Builder menuUrl(String menuUrl) {
      this.menuUrl = menuUrl;
      return this;
    }

    public Builder normalizePhone(String phone) {
      // only really works for american locale, but I'll cross that bridge later
      if (phone.length() < 10) {
        this.phone = phone;
      } else {
        this.phone = phone.replaceAll("\\(|\\)|\\-|\\+|\\.| ", "");
        if (this.phone.length() == 10) {
          this.phone =
              this.phone.substring(0, 3) + "-" + this.phone.substring(3, 6) + "-" + this.phone.substring(6, 10);
        }
      }
      return this;
    }

    public Builder twitterToken(String twitterToken) {
      this.twitterToken = twitterToken;
      return this;
    }

    public Builder twitterTokenSecret(String twitterTokenSecret) {
      this.twitterTokenSecret = twitterTokenSecret;
      return this;
    }

    public Builder neverLinkTwitter(boolean neverLinkTwitter) {
      this.neverLinkTwitter = neverLinkTwitter;
      return this;
    }

    public Builder postDailySchedule(boolean postDailySchedule) {
      this.postDailySchedule = postDailySchedule;
      return this;
    }

    public Builder postWeeklySchedule(boolean postWeeklySchedule) {
      this.postWeeklySchedule = postWeeklySchedule;
      return this;
    }

    public Builder postAtNewStop(boolean postAtNewStop) {
      this.postAtNewStop = postAtNewStop;
      return this;
    }

    public Builder clearTwitterCredentials() {
      this.neverLinkTwitter = true;
      this.twitterTokenSecret = null;
      this.twitterToken = null;
      return this;
    }
  }
}
