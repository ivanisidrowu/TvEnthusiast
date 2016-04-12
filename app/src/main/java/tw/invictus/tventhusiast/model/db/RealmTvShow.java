package tw.invictus.tventhusiast.model.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan on 9/21/15.
 */
public class RealmTvShow extends RealmObject{

    @PrimaryKey
    private Integer id;
    private String backdropPath;
    private String firstAirDate;
    private String overview;
    private String posterPath;
    private String title;
    private String lastAirDate;
    private Integer numberOfEpisodes;
    private Integer numberOfSeasons;
    private String status;
    private RealmList<RealmSeason> seasons;
    private RealmList<RealmGenre> genres;
    private Boolean isWatched;

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

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RealmList<RealmSeason> getSeasons(){
        return this.seasons;
    }

    public void setSeasons(RealmList<RealmSeason> seasons){
        this.seasons = seasons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RealmList<RealmGenre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<RealmGenre> genres) {
        this.genres = genres;
    }

    public Boolean getIsWatched() {
        return isWatched;
    }

    public void setIsWatched(Boolean isWatched) {
        this.isWatched = isWatched;
    }
}
