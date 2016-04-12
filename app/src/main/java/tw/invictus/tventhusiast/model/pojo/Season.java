package tw.invictus.tventhusiast.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 10/23/15.
 */
public class Season implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("name")
    private String title;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("season_number")
    private int seasonNumber;

    private boolean isWatched = false;

    @SerializedName("episodes")
    private List<Episode> episodes;

    public Season(){

    }

    public Season(long id, String airDate, String title, String overview, String posterPath, int seasonNumber, boolean isWatched, List<Episode> episodes) {
        this.id = id;
        this.airDate = airDate;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.seasonNumber = seasonNumber;
        this.isWatched = isWatched;
        this.episodes = episodes;
    }

    public Season(String title) {
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setIsWatched(boolean isWatched) {
        this.isWatched = isWatched;
    }

    public List<Episode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    protected Season(Parcel in) {
        id = in.readLong();
        airDate = in.readString();
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        seasonNumber = in.readInt();
        isWatched = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            episodes = new ArrayList<Episode>();
            in.readList(episodes, Episode.class.getClassLoader());
        } else {
            episodes = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(airDate);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeInt(seasonNumber);
        dest.writeByte((byte) (isWatched ? 0x01 : 0x00));
        if (episodes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(episodes);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Season> CREATOR = new Parcelable.Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };
}