package tw.invictus.tventhusiast.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ivan on 10/23/15.
 */

public class Episode implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("name")
    private String title;

    private String overview;

    @SerializedName("episode_number")
    private int episodeNumber;

    @SerializedName("season_number")
    private int seasonNumber;

    @SerializedName("still_path")
    private String stillPath;

    private int showId;

    private boolean isWatched = false;

    public Episode(){

    }

    public Episode(String title) {
        this.title = title;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setIsWatched(boolean isWatched) {
        this.isWatched = isWatched;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Episode(long id, String airDate, String title, String overview, int episodeNumber, int seasonNumber, String stillPath, int showId, boolean isWatched) {
        this.id = id;
        this.airDate = airDate;
        this.title = title;
        this.overview = overview;
        this.episodeNumber = episodeNumber;
        this.seasonNumber = seasonNumber;
        this.stillPath = stillPath;
        this.showId = showId;
        this.isWatched = isWatched;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.airDate);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeInt(this.episodeNumber);
        dest.writeInt(this.seasonNumber);
        dest.writeString(this.stillPath);
        dest.writeInt(this.showId);
        dest.writeByte(isWatched ? (byte) 1 : (byte) 0);
    }

    protected Episode(Parcel in) {
        this.id = in.readLong();
        this.airDate = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.episodeNumber = in.readInt();
        this.seasonNumber = in.readInt();
        this.stillPath = in.readString();
        this.showId = in.readInt();
        this.isWatched = in.readByte() != 0;
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        public Episode createFromParcel(Parcel source) {
            return new Episode(source);
        }

        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };
}