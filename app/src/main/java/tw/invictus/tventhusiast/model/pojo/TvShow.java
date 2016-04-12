package tw.invictus.tventhusiast.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 9/21/15.
 */
public class TvShow implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String firstAirDate;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("name")
    private String title;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("genres")
    private List<Genre> genres;

    private String status;


    private List<Season> seasons;

    public TvShow(){

    }

    public TvShow(int id, String backdropPath, String firstAirDate, String overview, String posterPath, String title, String lastAirDate, int numberOfEpisodes, int numberOfSeasons, List<Genre> genres, String status, List<Season> seasons) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.posterPath = posterPath;
        this.title = title;
        this.lastAirDate = lastAirDate;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.genres = genres;
        this.status = status;
        this.seasons = seasons;
    }

    public TvShow(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Season> getSeasons(){
        return this.seasons;
    }

    public void setSeasons(List<Season> seasons){
        this.seasons = seasons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }



    protected TvShow(Parcel in) {
        id = in.readInt();
        backdropPath = in.readString();
        firstAirDate = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        title = in.readString();
        lastAirDate = in.readString();
        numberOfEpisodes = in.readInt();
        numberOfSeasons = in.readInt();
        if (in.readByte() == 0x01) {
            genres = new ArrayList<Genre>();
            in.readList(genres, Genre.class.getClassLoader());
        } else {
            genres = null;
        }
        status = in.readString();
        if (in.readByte() == 0x01) {
            seasons = new ArrayList<Season>();
            in.readList(seasons, Season.class.getClassLoader());
        } else {
            seasons = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(backdropPath);
        dest.writeString(firstAirDate);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeString(lastAirDate);
        dest.writeInt(numberOfEpisodes);
        dest.writeInt(numberOfSeasons);
        if (genres == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genres);
        }
        dest.writeString(status);
        if (seasons == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(seasons);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}